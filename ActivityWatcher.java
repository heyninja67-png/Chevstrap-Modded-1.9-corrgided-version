package com.chevstrap.rbx.Integrations;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;

import com.chevstrap.rbx.Logger;
import com.chevstrap.rbx.UI.NotificationHey;
import com.chevstrap.rbx.Utility.FileToolAlt;

public class ActivityWatcher {
    private final String logLocation;
    private final Context context;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService serviceE;
    private String lastPlaceId;
    private String lastJobId;
    private AlertDialog currentDialogLastServer;

    public ActivityWatcher(Context context, String logFilePath, ExecutorService service) {
        this.context = context;
        this.logLocation = logFilePath;
        this.serviceE = service;
    }

    private void runOnUiThread(Runnable action) {
        mainHandler.post(action);
    }

    public void showLastServerDialog(String placeId, String instanceId) {
        if (currentDialogLastServer != null && currentDialogLastServer.isShowing()) {
            currentDialogLastServer.dismiss();
        }

        String deepLink = "https://www.roblox.com/games/start?placeId=" + placeId + "&gameInstanceId=" + instanceId;

        currentDialogLastServer = new AlertDialog.Builder(context)
                .setTitle("Message")
                .setMessage("Looks like you left the server, I caught your last server")
                .setCancelable(false)
                .setPositiveButton("Copy Deeplink", (dialog, id) -> {
                    try {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Roblox Server Link", deepLink);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(context, "Successfully copied to clipboard", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Failed to copy to clipboard", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel())
                .create();

        currentDialogLastServer.show();
    }

    public void start() {
        Context appContext = context.getApplicationContext();

        new Thread(() -> {
            Logger logger = new Logger(appContext);
            logger.initializePersistent();
            logger.writeLine("ActivityWatcher", "start() thread launched");

            try {
                boolean isDir = FileToolAlt.isDirectory(logLocation);
                logger.writeLine("ActivityWatcher", "Is directory: " + isDir);
                runOnUiThread(() -> Toast.makeText(appContext, "Checked if directory: " + isDir, Toast.LENGTH_SHORT).show());
            } catch (IOException e) {
                runOnUiThread(() -> Toast.makeText(appContext, "Invalid log dir: " + e.getMessage(), Toast.LENGTH_LONG).show());
                return;
            }

            File[] logsList = null;
            int waitTries = 0;
            while ((logsList == null || logsList.length == 0) && waitTries < 10) {
                try {
                    Thread.sleep(1000);
                    waitTries++;
                    if (!FileToolAlt.isRootAvailable()) {
                        logsList = new File(logLocation).listFiles();
                        logger.writeLine("ActivityWatcher", "Trying normal file access");
                    } else {
                        logsList = FileToolAlt.listFiles(logLocation);
                        logger.writeLine("ActivityWatcher", "Trying root file access");
                    }
                    if (logsList == null || logsList.length == 0) {
                        int finalWaitTries = waitTries;
                        runOnUiThread(() -> Toast.makeText(appContext, "Waiting for logs... (" + finalWaitTries + ")", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    logger.writeLine("ActivityWatcher", "Log scan retry failed: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(appContext, "Retrying logs: " + e.getMessage(), Toast.LENGTH_LONG).show());
                } catch (InterruptedException e) {
                    logger.writeLine("ActivityWatcher", "Interrupted while waiting for logs: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (logsList == null || logsList.length == 0) {
                logger.writeLine("ActivityWatcher", "Log files never appeared after waiting.");
                return;
            }

            Arrays.sort(logsList, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
            File latestLog = logsList[0];
            logger.writeLine("ActivityWatcher", "Monitoring log file: " + latestLog.getName());

            try (FileInputStream fis = new FileInputStream(latestLog);
                 FileChannel channel = fis.getChannel()) {

                long filePointer = channel.size();
                long lastModified = latestLog.lastModified();

                runOnUiThread(() -> Toast.makeText(appContext, "Monitoring latest log: " + latestLog.getName(), Toast.LENGTH_SHORT).show());

                while (!Thread.currentThread().isInterrupted()) {
                    if (latestLog.lastModified() != lastModified || latestLog.length() > filePointer) {
                        lastModified = latestLog.lastModified();

                        channel.position(filePointer);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Channels.newInputStream(channel)));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            ReadLogEntry(appContext, line);
                        }

                        filePointer = channel.position();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.writeLine("ActivityWatcher", "Log monitor interrupted during sleep.");
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            } catch (Exception e) {
                logger.writeLine("ActivityWatcher", "Error reading log file: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(appContext, "Error reading log: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }

    private void ReadLogEntry(Context context, String line) {
        final String joinGameStr = "[FLog::Output] ! Joining game";
        final String connectionStr = "[FLog::Output] Connection accepted from";

        if (line.contains("[FLog::Network] NetworkClient:Remove")) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null) nm.cancel(1001);
            runOnUiThread(() -> Toast.makeText(context, "Left game (NetworkClient:Remove)", Toast.LENGTH_SHORT).show());
        } else if (line.contains(joinGameStr)) {
            int placeIdStart = line.indexOf("place ") + 6;
            int placeIdEnd = line.indexOf(" at", placeIdStart);
            int jobIdStart = line.indexOf("'") + 1;
            int jobIdEnd = line.indexOf("'", jobIdStart);

            if (placeIdStart > 5 && placeIdEnd > placeIdStart && jobIdStart > 0 && jobIdEnd > jobIdStart) {
                lastPlaceId = line.substring(placeIdStart, placeIdEnd);
                lastJobId = line.substring(jobIdStart, jobIdEnd);
                runOnUiThread(() -> Toast.makeText(context, "Joining game: placeId=" + lastPlaceId + ", jobId=" + lastJobId, Toast.LENGTH_LONG).show());
            }
        } else if (line.contains(connectionStr)) {
            String[] parts = line.split(" ");
            String[] ipSplit = parts[parts.length - 1].split("\\|");

            if (ipSplit.length == 2) {
                String ip = ipSplit[0];
                NotificationHey.showConnectionNotification(context, ip);
                runOnUiThread(() -> Toast.makeText(context, "Connection accepted from: " + ip, Toast.LENGTH_SHORT).show());
            }
        }
    }
}

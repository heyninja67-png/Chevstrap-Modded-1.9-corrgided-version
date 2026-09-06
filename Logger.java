package com.chevstrap.rbx;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class Logger {
    private final Semaphore semaphore = new Semaphore(1);
    private FileOutputStream filestream;

    public final List<String> history = new ArrayList<>();
    public boolean initialized = false;
    public boolean noWriteMode = false;
    public String fileLocation;

    private final Context context;

    private static final String PREFS_NAME = "LoggerPrefs";
    private static final String KEY_LOG_FILE_PATH = "log_file_path";

    public Logger(Context context) {
        this.context = context;
    }

    public String getAsDocument() {
        return String.join("\n", history);
    }

    /**
     * Initialize logger with persistent log file:
     * Creates a new log file once, then reuses it on next app runs.
     */
    public void initializePersistent() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedPath = prefs.getString(KEY_LOG_FILE_PATH, null);

        File file = null;
        if (savedPath != null) {
            file = new File(savedPath);
            if (!file.exists() || !file.canWrite()) {
                file = null;
            }
        }

        if (file == null) {
            // Create new log file
            File directory = new File(context.getExternalFilesDir(null), "Logs");
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    showMessageBox("Failed to create log directory: " + directory.getAbsolutePath());
                    noWriteMode = true;
                    return;
                }
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US).format(new Date());
            String filename = "Chevstrap_" + timestamp + ".log";
            file = new File(directory, filename);

            try {
                if (!file.createNewFile()) {
                    // Log or handle if needed
                }
            } catch (IOException e) {
                showMessageBox("Failed to create log file: " + file.getAbsolutePath());
                noWriteMode = true;
                return;
            }

            prefs.edit().putString(KEY_LOG_FILE_PATH, file.getAbsolutePath()).apply();
        }

        try {
            // Open in append mode to continue existing log file
            filestream = new FileOutputStream(file, true);
            initialized = true;
            fileLocation = file.getAbsolutePath();
            writeLine("App::OnStartup", "Starting Chevstrap " + fileLocation);
            writeLine("Logger::Initialize", "Logger initialized at " + fileLocation);

            // If any history exists before initialize, flush it now
            if (!history.isEmpty()) {
                writeToLog(String.join("\r\n", history));
            }
        } catch (FileNotFoundException e) {
            noWriteMode = true;
            showMessageBox("Cannot write to log file: " + file.getAbsolutePath());
        }
    }

    public void writeLine(String identifier, String message) {
        writeLine("[" + identifier + "] " + message);
    }

    private void writeLine(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).format(new Date()) + "Z";
        String output = timestamp + " " + message;

        // Print to Logcat
        System.out.println(output);

        history.add(output);
        writeToLog(output);
    }

    public void writeException(String identifier, Exception ex) {
        String hresult = "0x" + Integer.toHexString(ex.hashCode()).toUpperCase(Locale.US);
        writeLine("[" + identifier + "] (" + hresult + ") " + ex.toString());
    }

    private void writeToLog(String message) {
        if (!initialized || filestream == null || noWriteMode) return;

        try {
            semaphore.acquire();
            filestream.write((message + "\r\n").getBytes(StandardCharsets.UTF_8));
            filestream.flush();
        } catch (IOException | InterruptedException ignored) {
        } finally {
            semaphore.release();
        }
    }

    private void showMessageBox(String message) {
        new Handler(Looper.getMainLooper()).post(() -> {
            new AlertDialog.Builder(context)
                    .setTitle("Logging Disabled")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show();
        });
    }
}

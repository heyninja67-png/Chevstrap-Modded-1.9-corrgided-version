package com.chevstrap.rbx;

import static com.chevstrap.rbx.Utility.INeedPath.getRBXPathDir;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.chevstrap.rbx.Integrations.ActivityWatcher;
import com.chevstrap.rbx.Utility.AppCheckerListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Watcher {
    private static Watcher currentInstance;
    private ExecutorService RBXActivityWatcher;
    private AppCheckerListener checker;
    public void run(Context context) {
        if (currentInstance != null) {
            currentInstance.stop();
        }
        currentInstance = this;

        checker = new AppCheckerListener(context, context.getPackageName(), new AppCheckerListener.Listener() {
            @Override
            public void onAppStarted() {
                FFlagsSettingsManager manager = new FFlagsSettingsManager(context);

                boolean getSetting1 = Boolean.parseBoolean(manager.getSetting("EnableActivityTracking"));
                boolean getSetting2 = Boolean.parseBoolean(manager.getSetting("ShowServerDetails"));

                if (!getSetting1 || !getSetting2) return;

                stopExecutorService(); // Ensure previous executor is stopped

                RBXActivityWatcher = Executors.newSingleThreadExecutor();
                String target = FFlagsSettingsManager.getPackageTarget(context);

                String rbxpath = getRBXPathDir(context, target);
                if (rbxpath == null) {
                    Toast.makeText(context, "Roblox path is null", Toast.LENGTH_SHORT).show();
                    return;
                }

                ActivityWatcher watcher = new ActivityWatcher(context, rbxpath.concat("appData/logs"), RBXActivityWatcher);
                RBXActivityWatcher.submit(watcher::start);
                Log.d("AppChecker", "Roblox has started.");
            }

            @Override
            public void onAppStopped() {
                stopExecutorService();
                Log.d("AppChecker", "Roblox has stopped.");
            }
        });

        checker.start();
    }

    private void stop() {
        if (checker != null) {
            checker.stop(); // You need to implement this in AppCheckerListener if not already
        }
        stopExecutorService();
    }

    private void stopExecutorService() {
        if (RBXActivityWatcher != null && !RBXActivityWatcher.isShutdown()) {
            RBXActivityWatcher.shutdownNow();
        }
    }
}

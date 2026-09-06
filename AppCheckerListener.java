package com.chevstrap.rbx.Utility;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;

import java.util.List;

public class AppCheckerListener {

    public interface Listener {
        void onAppStarted();
        void onAppStopped();
    }

    private final Context context;
    private final String targetPackageName;
    private final Listener listener;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean isMonitoring = false;
    private boolean lastKnownState = false;
    private static final long CHECK_INTERVAL_MS = 5000;

    public AppCheckerListener(Context context, String packageName, Listener listener) {
        this.context = context.getApplicationContext();
        this.targetPackageName = packageName;
        this.listener = listener;
    }

    public void start() {
        if (!isMonitoring) {
            isMonitoring = true;
            handler.post(checkRunnable);
        }
    }

    public void stop() {
        if (isMonitoring) {
            isMonitoring = false;
            handler.removeCallbacks(checkRunnable);
        }
    }

    private final Runnable checkRunnable = new Runnable() {
        @Override
        public void run() {
            boolean isRunning = isAppRunning(context, targetPackageName);
            if (isRunning != lastKnownState) {
                lastKnownState = isRunning;
                if (isRunning) {
                    listener.onAppStarted();
                } else {
                    listener.onAppStopped();
                }
            }

            if (isMonitoring) {
                handler.postDelayed(this, CHECK_INTERVAL_MS);
            }
        }
    };

    public static boolean isAppRunning(Context context, String packageName) {

        UsageStatsManager usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        if (usm == null) return false;

        long endTime = System.currentTimeMillis();
        long beginTime = endTime - DateUtils.MINUTE_IN_MILLIS;

        List<UsageStats> stats = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, beginTime, endTime);
        if (stats == null || stats.isEmpty()) return false;

        for (UsageStats usageStats : stats) {
            if (packageName.equals(usageStats.getPackageName()) &&
                    usageStats.getLastTimeUsed() >= beginTime) {
                return true;
            }
        }

        return false;
    }
}


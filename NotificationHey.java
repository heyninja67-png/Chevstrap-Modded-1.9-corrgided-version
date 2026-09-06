package com.chevstrap.rbx.UI;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.chevstrap.rbx.Logger;
import com.chevstrap.rbx.Models.Entities.ActivityData;
import com.chevstrap.rbx.R;

public class NotificationHey {
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static void runOnUiThread(Runnable action) {
        mainHandler.post(action);
    }
    public static void showConnectionNotification(Context context, String ip) {
        Logger logger = new Logger(context);
        logger.initializePersistent();

        runOnUiThread(() -> {
            android.app.Notification.Builder builder = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                    ? new android.app.Notification.Builder(context, "rbx_connection_channel")
                    : new android.app.Notification.Builder(context);
            ActivityData data = new ActivityData(ip);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            data.queryServerLocation(new ActivityData.LocationCallback() {
                @Override
                public void onLocationResolved(String location) {
                    String message = "Server location: " + location;
                    builder.setSmallIcon(R.drawable.chevstrap_logo)
                            .setContentTitle("Connected to server")
                            .setContentText(message)
                            .setAutoCancel(true);

                    logger.writeLine("NotifyIconWrapper::ShowAlert", message);
                    if (notificationManager != null) {
                        notificationManager.notify(1001, builder.build());
                    }
                }

                @Override
                public void onFailure() {
                    String failMsg = "Server location: Failed";
                    builder.setSmallIcon(R.drawable.chevstrap_logo)
                            .setContentTitle("Connected to server")
                            .setContentText("Location lookup failed")
                            .setAutoCancel(true);

                    logger.writeLine("NotifyIconWrapper::ShowAlert", failMsg);
                    if (notificationManager != null) {
                        notificationManager.notify(1001, builder.build());
                    }
                }
            });
        });
    }
}

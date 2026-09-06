package com.chevstrap.rbx.Utility;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.chevstrap.rbx.FFlagsSettingsManager;

public class INeedPath {

    public static String getRBXPathDir(Context context, String packageName) {
        boolean useLegacyPathWay1 = FFlagsSettingsManager.isExistSettingKey1(context, "UseLegacyFinderPath");

        if (useLegacyPathWay1) {
            String legacyPath = context.getFilesDir().getAbsolutePath();
            String replaced = legacyPath.replace(
                    "/" + context.getPackageName() + "/",
                    "/" + packageName + "/"
            );
            if (!replaced.endsWith("/")) {
                replaced += "/";
            }
            return replaced;
        } else {
            // Modern method: ApplicationInfo.dataDir
            try {
                ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, 0);
                return info.dataDir + "/files/";
            } catch (PackageManager.NameNotFoundException e) {
                return null; // Optional: fallback to legacy
            }
        }
    }


    public static String getRBXPathCach(Context context, String packageName) {
        String path = context.getCacheDir().getAbsolutePath();
        return path.replace(
                "/" + context.getPackageName() + "/",
                "/" + packageName + "/"
        );
    }
}

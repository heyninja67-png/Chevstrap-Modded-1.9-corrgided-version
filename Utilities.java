package com.chevstrap.rbx;

import android.util.Log;

public class Utilities {
    private static final String TAG = "Utilities";

    public static Process ShellExecute(String[] cmd) {
        try {
            return Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            Log.e(TAG, "Shell execution failed", e);
            return null;
        }
    }
}
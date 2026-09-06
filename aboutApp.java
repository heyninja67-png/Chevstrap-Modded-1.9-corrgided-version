package com.chevstrap.rbx.Utility;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class aboutApp {

    private final Context context;

    public aboutApp(Context context) {
        this.context = context;
    }

    public String getAppVersion() {
        try {
            return context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            return null;
        }
    }

    public String getLatestVersion() {
        try {
            URL url = new URL("https://api.github.com/repos/frossky/chevstrap/releases/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chevstrap-App"); // required
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return "no";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            JSONObject json = new JSONObject(response.toString());

            if (!json.has("tag_name")) {
                return "no";
            }

            return json.getString("tag_name").replaceFirst("^v", "");

        } catch (Exception e) {
            return "no";
        }
    }

    public boolean isInternetWorking() {
        return true;
    }
}

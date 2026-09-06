package com.chevstrap.rbx.Models.Entities;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.chevstrap.rbx.Models.APIs.IPInfoResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ActivityData {

    private final String targetIp;

    public ActivityData(String machineAddress) {
        this.targetIp = machineAddress;
    }

    private void fetchIPInfoIo(String ip, IPInfoCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("https://ipinfo.io/" + ip + "/json");
                JSONObject json = getJsonObject(url);

                new Handler(Looper.getMainLooper()).post(() -> {
                    callback.onSuccess(json);
                });

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    callback.onError(e);
                });
            }
        }).start();
    }

    @NonNull
    private static JSONObject getJsonObject(URL url) throws IOException, JSONException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();
        return new JSONObject(result.toString());
    }

    public interface IPInfoCallback {
        void onSuccess(JSONObject json);
        void onError(Exception e);
    }

    public interface LocationCallback {
        void onLocationResolved(String location);
        void onFailure();
    }

    public void queryServerLocation(LocationCallback callback) {
        fetchIPInfoIo(targetIp, new IPInfoCallback() {
            @Override
            public void onSuccess(JSONObject json) {
                try {
                    JSONObject ipInfo = IPInfoResponse.parse(json.toString());

                    if (ipInfo == null) {
                        callback.onFailure();
                        return;
                    }

                    String city = ipInfo.optString("city", "");
                    String region = ipInfo.optString("region", "");
                    String country = ipInfo.optString("country", "");

                    if (city.isEmpty()) {
                        callback.onFailure();
                        return;
                    }

                    String location;
                    if (city.equals(region)) {
                        location = region + ", " + country;
                    } else {
                        location = city + ", " + region + ", " + country;
                    }

                    callback.onLocationResolved(location);
                } catch (Exception e) {
                    callback.onFailure();
                }
            }

            @Override
            public void onError(Exception e) {
                callback.onFailure();
            }
        });
    }
}

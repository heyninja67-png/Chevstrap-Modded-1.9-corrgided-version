package com.chevstrap.rbx.Models.APIs;

import org.json.JSONObject;

public class IPInfoResponse {

    public static JSONObject parse(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject result = new JSONObject();
            result.put("city", jsonObject.getString("city"));
            result.put("country", jsonObject.getString("country"));
            result.put("region", jsonObject.getString("region"));

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}

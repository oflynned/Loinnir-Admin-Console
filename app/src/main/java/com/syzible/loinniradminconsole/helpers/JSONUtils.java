package com.syzible.loinniradminconsole.helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ed on 07/09/2017.
 */

public class JSONUtils {

    public static JSONObject getAuthPayload(Context context) {
        return getAuthPayload(LocalPrefs.getUsername(context), LocalPrefs.getSecret(context));
    }

    public static JSONObject getAuthLocalityPayload(Context context, String locality) {
        JSONObject o = getAuthPayload(context);
        try {
            o.put("locality", locality);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;
    }

    public static JSONObject getAuthConversationPayload(Context context, String[] pair) {
        JSONObject o = getAuthPayload(context);
        JSONArray a = new JSONArray();

        for (String id : pair)
            a.put(id);

        try {
            o.put("participants", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;
    }

    public static JSONObject getAuthPayload(String username, String secret) {
        JSONObject o = new JSONObject();
        try {
            o.put("username", username);
            o.put("secret", secret);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return o;
    }
}

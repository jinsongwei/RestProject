package com.williamjin.restproject;

import android.os.Bundle;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Created by william on 12/18/17.
 */

public class MessageUtil {

    public static Message getMessage(String data) {
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        Message message = new Message();
        message.setData(bundle);
        return message;
    }

    public static String getPrettyJson(String data){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(data);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}

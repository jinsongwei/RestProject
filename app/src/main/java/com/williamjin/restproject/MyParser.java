package com.williamjin.restproject;

import android.util.Log;

import com.google.gson.Gson;
import com.williamjin.restproject.model.github.MyGithubResponse;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


/**
 * Created by william on 12/18/17.
 */

public class MyParser {
    public static MyGithubResponse parseUsingGson(String response){
        Gson gson = new Gson();
        MyGithubResponse githubResponse = gson.fromJson(response, MyGithubResponse.class);
        return githubResponse;
    }

}

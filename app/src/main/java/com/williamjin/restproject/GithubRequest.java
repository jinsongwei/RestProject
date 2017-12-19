package com.williamjin.restproject;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by william on 12/18/17.
 */

public class GithubRequest extends Thread {
    String urlPath;
    Handler handler;

    public GithubRequest(String urlPath, Handler handler) {
        this.urlPath = urlPath;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL(urlPath);

            // open the connection using the HttpUrlConnection class
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // get the response using the input stream
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            // read the response from the input stream
            Scanner scanner = new Scanner(inputStream);

            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            Log.d(TAG, "run: url : " + urlPath);
            Log.d(TAG, "run: sending data: " + stringBuilder.toString() );
            handler.sendMessage(MessageUtil.getMessage(stringBuilder.toString()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

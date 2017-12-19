package com.williamjin.restproject;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.williamjin.restproject.data.remote.RemoteDataSource;
import com.williamjin.restproject.model.github.MyGithubResponse;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "https://api.github.com/";

    private TextView tvUserName;
    private TextView tvId;
    private TextView tvURL;
    private EditText etUsername;

    private Handler handler;
    private TextView tvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = findViewById(R.id.tv_username);
        tvId = findViewById(R.id.tv_id);
        tvURL = findViewById(R.id.tv_url);
        tvAvatar = findViewById(R.id.tv_avatar_url);

        etUsername = findViewById(R.id.et_username);
        handler = new Handler(this);

    }

    @Override
    public boolean handleMessage(Message msg) {
        String response = msg.getData().getString("data");
        MyGithubResponse myGithubResponse = MyParser.parseUsingGson(response);
        tvUserName.setText(myGithubResponse.getName());
        tvId.setText(Integer.toString(myGithubResponse.getId()));
        tvURL.setText(myGithubResponse.getUrl());
        tvAvatar.setText(myGithubResponse.getAvatarUrl());
//        Log.d(TAG, "handleMessage: username : " + myGithubResponse.getName() );
//        Log.d(TAG, "handleMessage: id " + myGithubResponse.getId());
//        Log.d(TAG, "handleMessage: url " + myGithubResponse.getUrl());
        return false;
    }

    public void btnGetProfileInfo(View view) {
        String username = etUsername.getText().toString();
        GithubRequest request = new GithubRequest(BASE_URL + "users/" + username, handler);
        request.start();
    }


}

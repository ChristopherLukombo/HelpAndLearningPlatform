package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trips.Fragment.MarkFragment;
import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TrickActivity extends BaseActivity {

    private Trick trick;
    private TextView trickTitle, trickAuthor, trickCategory,trickContent;
    private Button buttonFinish, buttonUnsubscribe;
    private Fragment markFragment;
    private String url;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trick);
        trickTitle = findViewById(R.id.trickTitle);
        trickAuthor = findViewById(R.id.trickAuthor);
        trickCategory = findViewById(R.id.trickCategory);
        trickContent = findViewById(R.id.trickContent);
        buttonFinish = findViewById(R.id.buttonFinish);
        buttonUnsubscribe = findViewById(R.id.trickUnsubscribeButton);
        markFragment = new MarkFragment();
        url = getString(R.string.api_url);

        Intent intent = getIntent();
        trick = (Trick) intent.getSerializableExtra("trick");
        userId = (long) intent.getLongExtra("userId", 0);
        setValues();
    }

    private void setValues() {
        trickTitle.setText(trick.getName());
        trickAuthor.setText(trick.getUser().getPseudo());
        trickCategory.setText(trick.getCategory().getName());
        trickContent.setText(trick.getContent());

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTrick();
            }
        });

        buttonUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unsubscribeTrick();
            }
        });
        if(trick.getSubscription().isFinished()){
            buttonUnsubscribe.setVisibility(View.GONE);
            buttonFinish.setVisibility(View.GONE);

            setFragment();
        }
    }

    private void unsubscribeTrick() {
        String fullUrl = this.url + "subscriptions/" + trick.getSubscription().getId();
        final VolleyJSONArrayCallback subscriptionVolleyCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray response) {
                onBackPressed();
            }

        };

        HTTPRequestHelper.deleteRequest(getApplicationContext(), fullUrl, subscriptionVolleyCallback, getToken());
    }

    private void setFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.markLayout, markFragment).commit();
        ((MarkFragment) markFragment).setData(this.trick, getToken(), userId);

    }

    private void finishTrick() {
        String fullUrl = this.url + "subscriptions/finished/?subscriptionId=" + trick.getSubscription().getId() +"&userId=" + userId;
        final VolleyJSONObjectCallback subscriptionVolleyCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                trick.getSubscription().setFinished(true);
                buttonUnsubscribe.setVisibility(View.GONE);
                setFragment();
            }

        };

        HTTPRequestHelper.patchRequest(getApplicationContext(), fullUrl, subscriptionVolleyCallback, getToken(), new JSONObject(new HashMap<String, String>()));
    }

    private JSONObject makeSubscriptionBody(long subscriptionId) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("subscriptionId", String.valueOf(subscriptionId));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

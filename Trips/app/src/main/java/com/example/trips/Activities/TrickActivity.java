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
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class TrickActivity extends BaseActivity {

    private Trick trick;
    private TextView trickTitle, trickAuthor, trickCategory,trickContent;
    private Button buttonFinish, buttonUnsubscribe;
    private Fragment markFragment;
    private String url;

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
                //finishTrick();
                setFragment();
            }
        });

        buttonUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unsubscribeTrick();
            }
        });
    }

    private void unsubscribeTrick() {
        String fullUrl = this.url + "subscriptions";
        final VolleyJSONObjectCallback subscriptionVolleyCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse() {
                onBackPressed();
            }
        };

        HTTPRequestHelper.deleteRequest(getApplicationContext(), fullUrl, subscriptionVolleyCallback, getToken(), makeSubscriptionBody(trick.getSubscription().getId()));
    }

    private void setFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.markLayout, markFragment).commit();
        ((MarkFragment) markFragment).setData(this.trick, getToken());

    }

    private void finishTrick() {

    }

    private JSONObject makeSubscriptionBody(long subscriptionId) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id", String.valueOf(subscriptionId));
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

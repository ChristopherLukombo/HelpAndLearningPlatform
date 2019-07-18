package com.example.trips.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trips.Fragment.MarkFragment;
import com.example.trips.Fragment.PopupFragment;
import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TrickActivity extends BaseActivity {

    private Trick trick;
    private TextView trickTitle, trickAuthor, trickCategory,trickContent;
    private Button buttonFinish, buttonUnsubscribe, commentButton;
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
        commentButton = findViewById(R.id.commentButton);
        buttonUnsubscribe = findViewById(R.id.trickUnsubscribeButton);
        markFragment = new MarkFragment();
        url = getString(R.string.api_url);

        Intent intent = getIntent();
        trick = (Trick) intent.getSerializableExtra("trick");
        userId = (long) intent.getLongExtra("userId", 0);
        postView();
        setValues();
    }

    private void postView() {
        String fullUrl = this.url + "tricks/addView/"+ trick.getId();

        final VolleyJSONObjectCallback volleyCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
            }

        };

        HTTPRequestHelper.patchRequest(getApplicationContext(), fullUrl, volleyCallback, getToken(), new JSONObject(new HashMap<String, String>()));
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

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopup();
            }
        });
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
                buttonFinish.setVisibility(View.GONE);
                setFragment();
                addNotification();
            }

        };

        HTTPRequestHelper.patchRequest(getApplicationContext(), fullUrl, subscriptionVolleyCallback, getToken(), new JSONObject(new HashMap<String, String>()));
    }

    private void openPopup() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        PopupFragment popupFragment = new PopupFragment();
        popupFragment.setData(trick, userId);
        popupFragment.show(fragmentManager, "comments");
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

    private void addNotification() {
        String message = "Vous avez apprecié l'astuce ? Il en existe d'autres dans la catégorie " + this.trick.getCategory().getName() + ", jetez un oeil !";
        Intent intent  = new Intent(this, TricksListActivity.class);
        intent.putExtra("category", this.trick.getCategory().getName());
        intent.putExtra("userId", this.userId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notif = new Notification.Builder(this)
                .setSmallIcon(R.drawable.trips_logo)
                .setAutoCancel(true)
                .setStyle(new Notification.BigTextStyle().bigText(message))
                .setContentTitle("Trips")
                .setContentText(message)
                .setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, notif.build());
    }
}

package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Helpers.JSONHelper;
import com.example.trips.Models.Category;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Subscription;
import com.example.trips.Models.Trick;
import com.example.trips.Models.User;
import com.example.trips.R;
import com.example.trips.TrickAdapter;
import com.example.trips.TrickCustomClickListener;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity  {

    private RecyclerView mostRecentTricksRecyclerView;
    private RecyclerView mostViewedTricksRecyclerView;
    private List<Trick> mostRecentTricks;
    private List<Trick> mostViewedTricks;
    private List<Category> categories;
    private List<User> users;
    private List<Subscription> subscriptions;
    private long userId;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostRecentTricks = new ArrayList<>();
        mostViewedTricks = new ArrayList<>();
        categories = new ArrayList<>();
        users = new ArrayList<>();
        subscriptions = new ArrayList<>();
        url = getString(R.string.api_url);

        setUserId();
        getData();
    }

    private void setUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        userId = sharedPreferences.getLong("USERID", 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initRecyclerViews(){
        mostRecentTricksRecyclerView = findViewById(R.id.mostRecentTricks);
        mostViewedTricksRecyclerView = findViewById(R.id.mostViewedTricks);
        mostRecentTricksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mostViewedTricksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mostRecentTricksRecyclerView.setAdapter(new TrickAdapter(getApplicationContext(), mostRecentTricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick trick) {
                if(trick.isSubscribed()){
                    Intent intent = new Intent(MainActivity.this, TrickActivity.class);
                    intent.putExtra("trick", trick);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
                else{
                    trick.setSubscribed(true);
                    subscribeTrick(mostRecentTricks ,trick, userId);
                }
            }
        }));

        mostViewedTricksRecyclerView.setAdapter(new TrickAdapter(getApplicationContext(), mostViewedTricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick trick) {
                if(trick.isSubscribed()){
                    Intent intent = new Intent(MainActivity.this, TrickActivity.class);
                    intent.putExtra("trick", trick);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                }
                else{
                    trick.setSubscribed(true);
                    subscribeTrick(mostViewedTricks ,trick, userId);
                }
            }
        }));
    }

    private void getData(){
        String mostViewedTricksUrl = url+ "tricks/mostviewed";
        String mostLatestTricksUrl = url+ "tricks/mostlatests";
        final String categoryUrl = this.url + "categories/all";
        final String userUrl = this.url + "users";
        final String subscriptionUrl = this.url + "subscriptions";
        final JSONArray[] jsonArrays = new JSONArray[5];
        final String token = getToken();

        final VolleyJSONArrayCallback subscriptionVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[4] = result;
                setData(jsonArrays);
            }
        };

        final VolleyJSONArrayCallback userVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[3] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), subscriptionUrl, subscriptionVolleyJSONArrayCallback, token);
            }
        };

        final VolleyJSONArrayCallback categoryVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[2] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), userUrl, userVolleyJSONArrayCallback, token);
            }
        };

        VolleyJSONArrayCallback mostViewedVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[1] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), categoryUrl, categoryVolleyJSONArrayCallback, token);
            }
        };

        VolleyJSONArrayCallback mostRecentVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[0] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), mostViewedTricksUrl, mostViewedVolleyJSONArrayCallback, token);
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(), mostLatestTricksUrl, mostRecentVolleyJSONArrayCallback,token);

    }

    private void setData(JSONArray[] jsonArrays) {
        this.mostViewedTricks = JSONHelper.trickListFromJSONObject(jsonArrays[0]);
        this.mostRecentTricks = JSONHelper.trickListFromJSONObject(jsonArrays[1]);
        this.categories = JSONHelper.categoryListFromJSONObject(jsonArrays[2]);
        this.users = JSONHelper.userListFromJSONObject(jsonArrays[3]);
        this.subscriptions = JSONHelper.subscribtionListFromJSONObject(jsonArrays[4]);

        setSubscriptions();
        setTricksCategories();
        setTricksUsers();
        setTricksMark();
    }

    private void setSubscriptions() {
        for (Trick trick: mostViewedTricks){
            for (Subscription subscription: subscriptions) {
                if(trick.getId() == subscription.getTrickId() && subscription.getUserId() == this.userId){
                    trick.setSubscribed(true);
                    trick.setSubscription(subscription);
                }
            }
        }
        for (Trick trick: mostRecentTricks){
            for (Subscription subscription: subscriptions) {
                if(trick.getId() == subscription.getTrickId() && subscription.getUserId() == this.userId){
                    trick.setSubscribed(true);
                    trick.setSubscription(subscription);
                }
            }
        }
    }

    private void setTricksMark() {

        String markUrl = this.url + "notations/trick/";
        for (final Trick trick: mostViewedTricks){
            String completeUrl = markUrl + trick.getId();

            VolleyJSONArrayCallback markVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
                @Override
                public void onResponse(JSONArray result) {
                    double totalMark = 0;

                    if(result != null){
                        List<Mark> list = JSONHelper.markListFromJSONObject(result);

                        for(Mark mark : list){
                            totalMark += mark.getNote();

                            if(mark.getUserId() == userId){
                                trick.setMark(mark);
                            }
                        }

                        totalMark = totalMark / list.size();
                    }

                    trick.setMarkNotation(totalMark);
                }

            };
            HTTPRequestHelper.getRequest(getApplicationContext(), completeUrl, markVolleyJSONArrayCallback, getToken());
        }
        for (final Trick trick: mostRecentTricks){
            String completeUrl = markUrl + trick.getId();

            VolleyJSONArrayCallback markVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
                @Override
                public void onResponse(JSONArray result) {
                    double totalMark = 0;

                    if(result != null){
                        List<Mark> list = JSONHelper.markListFromJSONObject(result);

                        for(Mark mark : list){
                            totalMark += mark.getNote();
                            if(mark.getUserId() == userId){
                                trick.setMark(mark);
                            }
                        }

                        totalMark = totalMark / list.size();
                    }

                    trick.setMarkNotation(totalMark);

                    if(mostRecentTricks.indexOf(trick) == (mostRecentTricks.size() -1)){
                        initRecyclerViews();
                    }
                }

            };
            HTTPRequestHelper.getRequest(getApplicationContext(), completeUrl, markVolleyJSONArrayCallback, getToken());
        }
    }

    private void setTricksCategories() {
        for (Trick trick: mostRecentTricks){
            for (Category category: categories) {
                if(trick.getCategoryId() == category.getId()){
                    trick.setCategory(category);
                }
            }
        }
        for (Trick trick: mostViewedTricks){
            for (Category category: categories) {
                if(trick.getCategoryId() == category.getId()){
                    trick.setCategory(category);
                }
            }
        }
    }

    private void setTricksUsers() {
        for (Trick trick: mostRecentTricks){
            for (User user: users) {
                if(trick.getUserId() == user.getId()){
                    trick.setUser(user);
                }
            }
        }
        for (Trick trick: mostViewedTricks){
            for (User user: users) {
                if(trick.getUserId() == user.getId()){
                    trick.setUser(user);
                }
            }
        }
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }

    private void subscribeTrick(List<Trick> tricks, final Trick trick, long userId) {
        final String subscriptionUrl = this.url + "subscriptions";

        final VolleyJSONObjectCallback subscriptionVolleyCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                Subscription subscription = null;
                try {
                    subscription = new Subscription(response.getString("subscriptionDate") , userId, trick.getId());
                    subscription.setId(response.getLong("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                trick.setSubscription(subscription);
                int trickIndex = tricks.indexOf(trick);
                tricks.set(trickIndex, trick);
                initRecyclerViews();
            }

        };

        HTTPRequestHelper.postRequest(getApplicationContext(), subscriptionUrl, subscriptionVolleyCallback, getToken(), makeSubscriptionBody(trick.getId()));
    }

    private JSONObject makeSubscriptionBody(long trickId) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("trickId", String.valueOf(trickId));
            jsonBody.put("userId", String.valueOf(this.userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }


}

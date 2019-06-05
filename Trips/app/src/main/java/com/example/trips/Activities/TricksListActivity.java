package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Helpers.JSONHelper;
import com.example.trips.Models.Category;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Trick;
import com.example.trips.Models.User;
import com.example.trips.R;
import com.example.trips.TrickAdapter;
import com.example.trips.TrickCustomClickListener;
import com.example.trips.VolleyCallback;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TricksListActivity extends BaseActivity {

    private RecyclerView tricksRecyclerView;
    private List<Trick> tricks;
    private List<Category> categories;
    private List<User> users;
    private List<Mark> marks;
    private long userId;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricks_list);
        super.useToolbar();
        Intent intent = getIntent();
        url = getString(R.string.api_url);

        tricks = new ArrayList<>();
        categories = new ArrayList<>();
        users = new ArrayList<>();

        handleIntent(intent);
    }

    private void setAdapter(){
        tricksRecyclerView = findViewById(R.id.trickListRecyclerView);
        tricksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tricksRecyclerView.setAdapter(new TrickAdapter(getApplicationContext(), tricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick trick) {
                Intent intent = new Intent(TricksListActivity.this, TrickActivity.class);
                intent.putExtra("trick", trick);
                startActivity(intent);
            }
        }));
    }

    private void handleIntent(Intent intent) {
        String value = intent.getExtras().getString("TRICKS");

        if(!"ALL".equals(value)){
            getData(this.userId);
        }
        else{
            getData(new Long(0));
        }
    }

    private void getData(Long id){
        String tricksUrl = makeTrickUrl(id, "tricks");
        final String categoryUrl = this.url + "categories/all";
        final String userUrl = this.url + "categories/all";
        final String markUrl = this.url + "notations/trick/";
        final JSONArray[] jsonArrays = new JSONArray[4];
        final String token = getToken();



        final VolleyCallback userVolleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[2] = result;
                setData(jsonArrays);
            }
        };

        final VolleyCallback categoryVolleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[1] = result;
                setData(jsonArrays);
                //HTTPRequestHelper.getRequest(getApplicationContext(), userUrl, userVolleyCallback, token);
            }
        };

        VolleyCallback trickVolleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[0] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), categoryUrl, categoryVolleyCallback, token);
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(), tricksUrl, trickVolleyCallback,token);

    }

    private void setData(JSONArray[] jsonArrays) {
        this.tricks = JSONHelper.trickListFromJSONObject(jsonArrays[0]);
        this.categories = JSONHelper.categoryListFromJSONObject(jsonArrays[1]);
        this.users = JSONHelper.userListFromJSONObject(jsonArrays[2]);
        this.marks = JSONHelper.markListFromJSONObject(jsonArrays[3]);

        setTricksMark();
        setTricksCategories();
        //setTricksUsers();
        setTricksMark();

        setAdapter();
    }

    private void setTricksUsers() {
        for (Trick trick: tricks){
            for (User user: users) {
                if(trick.getUserId() == user.getId()){
                    trick.setUser(user);
                }
            }
        }
    }

    private void setTricksMark() {

        String markUrl = this.url + "notations/trick/";
        for (final Trick trick: tricks){
            markUrl +=  "?trickId=" + trick.getId();

            VolleyCallback markVolleyCallback = new VolleyCallback() {
                @Override
                public void onResponse(JSONArray result) {
                    List<Mark> marks = JSONHelper.markListFromJSONObject(result);

                    trick.setMark(marks.get(0));
                }
            };

            HTTPRequestHelper.getRequest(getApplicationContext(), markUrl, markVolleyCallback, getToken());
        }
    }

    private void setTricksCategories() {
        for (Trick trick: tricks){
            for (Category category: categories) {
                if(trick.getCategoryId() == category.getId()){
                    trick.setCategory(category);
                }
            }
        }
    }

    private String makeTrickUrl(Long id, String urlExtension){
        String newUrl = this.url + urlExtension;
        if(!id.equals(0)){
            newUrl += "?userId=" + id.toString();
        }

        return newUrl;
    }


    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

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
import com.example.trips.Models.Trick;
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
        final String markUrl = this.url + "catgories/all";
        final JSONArray[] jsonArrays = new JSONArray[3];
        final String token = getToken();

        final VolleyCallback categoryVolleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[1] = result;
                setData(jsonArrays);
            }
        };

        final VolleyCallback markVolleyCallback = new VolleyCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[2] = result;
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
        setTricksCategories();
        setmarks(jsonArrays[2]);
        setAdapter();
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

    private void setmarks(JSONArray markJSONArray){

    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

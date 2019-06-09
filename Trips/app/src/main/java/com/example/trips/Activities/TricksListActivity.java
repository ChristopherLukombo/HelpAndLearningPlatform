package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

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
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TricksListActivity extends BaseActivity {

    private RecyclerView tricksRecyclerView;
    private SearchView searchView;
    private Button sortNameButton, sortDateButton, sortMarkButton;
    private Spinner spinner;
    boolean nameAscending = true;
    boolean dateAscending = true;
    boolean markAscending = true;
    private List<Trick> tricks;
    private List<Category> categories;
    private List<User> users;
    private List<Subscription> subscriptions;
    private long userId = 7;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricks_list);
        super.useToolbar();
        searchView = findViewById(R.id.action_search);
        spinner = findViewById(R.id.categorySpinner);
        sortDateButton = findViewById(R.id.sortDateButton);
        sortNameButton = findViewById(R.id.sortByNameButton);
        sortMarkButton = findViewById(R.id.sortMarkButton);
        Intent intent = getIntent();
        url = getString(R.string.api_url);

        tricks = new ArrayList<>();
        categories = new ArrayList<>();
        users = new ArrayList<>();
        subscriptions = new ArrayList<>();

        handleIntent(intent);
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
        final String userUrl = this.url + "users";
        final String subscriptionUrl = this.url + "subscriptions";
        final JSONArray[] jsonArrays = new JSONArray[4];
        final String token = getToken();

        final VolleyJSONArrayCallback subscriptionVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[3] = result;
                setData(jsonArrays);
            }
        };

        final VolleyJSONArrayCallback userVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[2] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), subscriptionUrl, subscriptionVolleyJSONArrayCallback, token);
            }
        };

        final VolleyJSONArrayCallback categoryVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[1] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), userUrl, userVolleyJSONArrayCallback, token);
            }
        };

        VolleyJSONArrayCallback trickVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray result) {
                jsonArrays[0] = result;
                HTTPRequestHelper.getRequest(getApplicationContext(), categoryUrl, categoryVolleyJSONArrayCallback, token);
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(), tricksUrl, trickVolleyJSONArrayCallback,token);

    }

    private void setData(JSONArray[] jsonArrays) {
        this.tricks = JSONHelper.trickListFromJSONObject(jsonArrays[0]);
        this.categories = JSONHelper.categoryListFromJSONObject(jsonArrays[1]);
        this.users = JSONHelper.userListFromJSONObject(jsonArrays[2]);
        this.subscriptions = JSONHelper.subscribtionListFromJSONObject(jsonArrays[3]);

        setSubscriptions();
        setTricksCategories();
        setTricksUsers();
        setTricksMark();
    }

    private void setSubscriptions() {
        for (Trick trick: tricks){
            for (Subscription subscription: subscriptions) {
                if(trick.getId() == subscription.getTrickId() && subscription.getUserId() == this.userId){
                    trick.setSubscribed(true);
                }
            }
        }
    }

    private void setTricksMark() {

        String markUrl = this.url + "notations/trick/";
        for (final Trick trick: tricks){
            String completeUrl = markUrl + trick.getId();

            VolleyJSONArrayCallback markVolleyJSONArrayCallback = new VolleyJSONArrayCallback() {
                @Override
                public void onResponse(JSONArray result) {
                    double totalMark = 0;

                    if(result != null){
                        List<Mark>  list = JSONHelper.markListFromJSONObject(result);

                        for(Mark mark : list){
                            totalMark += mark.getNote();
                        }

                        totalMark = totalMark / list.size();
                    }

                    trick.setMark(totalMark);

                    if(tricks.indexOf(trick) == (tricks.size() -1)){
                        setAdapter();
                    }
                }

            };

            HTTPRequestHelper.getRequest(getApplicationContext(), completeUrl, markVolleyJSONArrayCallback, getToken());

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

    private void setSpinner() {
        List<String> categoriesName = new ArrayList<String>();
        categoriesName.add("Catégories");
        for (Category category: categories) {
            categoriesName.add(category.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
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

    private void setAdapter(){
        tricksRecyclerView = findViewById(R.id.trickListRecyclerView);
        tricksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSpinner();
        final RecyclerView.Adapter adapter = new TrickAdapter(getApplicationContext(), tricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick trick) {
                if(trick.isSubscribed()){
                    Intent intent = new Intent(TricksListActivity.this, TrickActivity.class);
                    intent.putExtra("trick", trick);
                    startActivity(intent);
                }
                else{
                    trick.setSubscribed(true);
                    subscribeTrick(trick, userId);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ((TrickAdapter) adapter).getFilter().filter("str"+ s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ((TrickAdapter) adapter).getFilter().filter("str"+ s);
                return true;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ((TrickAdapter) adapter).getFilter().filter("cat");
                }
                else{
                    ((TrickAdapter) adapter).getFilter().filter("cat"+ spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sortNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TrickAdapter) adapter).sortByName(nameAscending);
                nameAscending = ! nameAscending;
            }
        });

        sortDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TrickAdapter) adapter).sortByDate(dateAscending);
                dateAscending = ! dateAscending;
            }
        });

        sortMarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TrickAdapter) adapter).sortByMark(markAscending);
                markAscending = ! markAscending;
            }
        });

        tricksRecyclerView.setAdapter(adapter);
    }

    private void subscribeTrick(final Trick trick, long userId) {
        final String subscriptionUrl = this.url + "subscriptions";

        final VolleyJSONObjectCallback subscriptionVolleyCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse() {
                int trickIndex = tricks.indexOf(trick);
                tricks.set(trickIndex, trick);
                setAdapter();
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

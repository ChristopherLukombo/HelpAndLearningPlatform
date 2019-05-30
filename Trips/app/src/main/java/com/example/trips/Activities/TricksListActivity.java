package com.example.trips.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.trips.Models.Category;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.TrickAdapter;
import com.example.trips.TrickCustomClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TricksListActivity extends BaseActivity{

    private RecyclerView tricksRecyclerView;
    private ArrayList<Trick> tricks;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricks_list);
        super.useToolbar();
        Intent intent = getIntent();

        //handleIntent(intent);

        tricks = new ArrayList<>();
        Category langage = new Category("Langues");
        Category autre = new Category("autres");
        Category jeuxVideos = new Category("Jeux Video");


        tricks.add(new Trick(1,1, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais", "This a ultra fucking Huge text \n to have some ultra fun \n Yeah \n really mega fun.\n\n" +
                "                            Firstable just fucking finish this trick and make some monkeys drink some milk. They would love that !!!! \n \n Holly crap it's late and i'm really hungry.\n\n\n" +
                "                            plesae help me i need help.ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd\n\nggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"));
        tricks.add(new Trick(1, 1, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY", "Contenu de l'astuce"));
        tricks.add(new Trick(1, 1,"12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser", "Contenu de l'astuce"));

        tricks.add(new Trick(1, 1, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais", "Contenu de l'astuce"));
        tricks.add(new Trick(1, 1, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY", "Contenu de l'astuce"));
        tricks.add(new Trick(1, 1, "12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser", "Contenu de l'astuce"));

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

        if("ALL".equals(value)){
            getAllTricks();
        }
        else{
            getUserTricks();
        }
    }

    private void getUserTricks() {
        getTricks(this.userId);
    }


    private void getAllTricks() {
        getTricks(new Long(0));
    }

    private void getTricks(Long id){
        String url = makeUrl(id);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = response.getJSONObject(i);


                            }
                        }
                        catch (JSONException exception){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "PAS POSSIBLE", Toast.LENGTH_LONG).show();
                    }
                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }

    private String makeUrl(Long id){
        String url = getString(R.string.api_url) + "tricks/";
        if(!id.equals(0)){
            url += "?userId=" + id.toString();
        }

        return url;
    }

    private void addTricksToList(JSONObject jsonObject){
        Trick trick = fromJSONTrickToObject(jsonObject);

        this.tricks.add(trick);
    }

    private Trick fromJSONTrickToObject(JSONObject jsonObject){
        //Trick trick = new Trick(null, null, null, null, null, null);

        return null;//trick;
    }
}

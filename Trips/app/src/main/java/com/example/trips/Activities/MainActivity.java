package com.example.trips.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trips.Models.Category;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.TrickAdapter;
import com.example.trips.TrickCustomClickListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity  {

    private RecyclerView mostRecentTricksRecyclerView;
    private RecyclerView mostViewedTricksRecyclerView;
    private ArrayList<Trick> mostRecentTricks;
    private ArrayList<Trick> mostViewedTricks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostRecentTricks = new ArrayList<>();
        mostViewedTricks = new ArrayList<>();

        Category langage = new Category("Langues");
        Category autre = new Category("autres");
        Category jeuxVideos = new Category("Jeux Video");


        mostRecentTricks.add(new Trick(langage, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais"));
        mostRecentTricks.add(new Trick(autre, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY"));
        mostRecentTricks.add(new Trick(jeuxVideos, "12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser"));

        mostViewedTricks.add(new Trick(langage, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais"));
        mostViewedTricks.add(new Trick(autre, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY"));
        mostViewedTricks.add(new Trick(jeuxVideos, "12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser"));

        initRecyclerViews();
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
            public void onTrickItemClick(View v, Trick appointment) {

            }
        }));

        mostViewedTricksRecyclerView.setAdapter(new TrickAdapter(getApplicationContext(), mostViewedTricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick appointment) {

            }
        }));
    }


}

package com.example.trips.Activities;

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

import com.example.trips.Models.Category;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.TrickAdapter;
import com.example.trips.TrickCustomClickListener;

import java.util.ArrayList;

public class TricksListActivity extends BaseActivity{

    private RecyclerView tricksRecyclerView;
    private ArrayList<Trick> tricks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tricks_list);
        super.useToolbar();

        tricks = new ArrayList<>();
        Category langage = new Category("Langues");
        Category autre = new Category("autres");
        Category jeuxVideos = new Category("Jeux Video");


        tricks.add(new Trick(langage, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais"));
        tricks.add(new Trick(autre, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY"));
        tricks.add(new Trick(jeuxVideos, "12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser"));
        tricks.add(new Trick(langage, "12/04/2019", "Un petit guide pour vous aider à apprendre l'anglais plus facilement.", "Apprendre l'anglais"));
        tricks.add(new Trick(autre, "12/04/2019", "Le trick de ses morts qui va changer ta vie de ses morts", "TRICKY"));
        tricks.add(new Trick(jeuxVideos, "12/04/2019", "Configure ton pc pour jouer aux fps de manère optimale", "FPS Optimiser"));

        tricksRecyclerView = findViewById(R.id.trickListRecyclerView);
        tricksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tricksRecyclerView.setAdapter(new TrickAdapter(getApplicationContext(), tricks, new TrickCustomClickListener() {
            @Override
            public void onTrickItemClick(View v, Trick appointment) {

            }
        }));
    }
}

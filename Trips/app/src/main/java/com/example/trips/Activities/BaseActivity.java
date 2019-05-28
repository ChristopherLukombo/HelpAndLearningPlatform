package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.trips.R;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);

        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, fullView, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        fullView.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected boolean useToolbar()
    {
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_home) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tricks) {
            intent = new Intent(getApplicationContext(), TricksListActivity.class);
            intent.putExtra("TRICKS", "ALL");
            startActivity(new Intent(getApplicationContext(), TricksListActivity.class));

        } else if (id == R.id.nav_followed_tricks) {
            intent = new Intent(getApplicationContext(), TricksListActivity.class);
            intent.putExtra("TRICKS", "FOLLOWED");
            startActivity(intent);
        } else if (id == R.id.nav_finished_tricks) {
            intent = new Intent(getApplicationContext(), TricksListActivity.class);
            intent.putExtra("TRICKS", "FINISHED");
            startActivity(intent);

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_profile) {
            // Handle the camera action
        }
        else if (id == R.id.nav_deconnexion) {
            clearSharedPreferences();
            finishAffinity();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearSharedPreferences(){
        SharedPreferences prefs = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LOGIN_PSEUDO", "");
        editor.putString("LOGIN_PWD", "");
        editor.commit();
    }
}



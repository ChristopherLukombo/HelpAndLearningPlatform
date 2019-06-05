package com.example.trips.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.trips.Fragment.MarkFragment;
import com.example.trips.Models.Trick;
import com.example.trips.R;

public class TrickActivity extends BaseActivity {

    private Trick trick;
    private TextView trickTitle, trickAuthor, trickCategory,trickContent;
    private Button buttonFinish;
    private Fragment markFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trick);
        trickTitle = findViewById(R.id.trickTitle);
        trickAuthor = findViewById(R.id.trickAuthor);
        trickCategory = findViewById(R.id.trickCategory);
        trickContent = findViewById(R.id.trickContent);
        buttonFinish = findViewById(R.id.buttonFinish);
        markFragment = new MarkFragment();

        Intent intent = getIntent();
        trick = (Trick) intent.getSerializableExtra("trick");
        setValues();
    }

    private void setValues() {
        trickTitle.setText(trick.getName());
        trickAuthor.setText(trick.getUser().getFirstName() + " " + trick.getUser().getLastName() );
        trickCategory.setText(trick.getCategory().getName());
        trickContent.setText(trick.getContent());

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finishTrick();
                setFragment();
            }
        });
    }

    private void setFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.markLayout, markFragment).commit();
    }

    private void finishTrick() {
    }
}

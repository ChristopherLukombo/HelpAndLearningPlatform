package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trips.Helpers.AuthenticatorHelper;
import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Models.User;
import com.example.trips.R;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONObject;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFirstname, inputLastname;
    private Button btnSave;
    private ProgressBar progressBar ;
    private String url;
    private User user;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        inputEmail = findViewById(R.id.profileMailInput);
        inputPassword = findViewById(R.id.profilePasswordInput);
        inputFirstname = findViewById(R.id.profileFirstNameInput);
        inputLastname = findViewById(R.id.profileLastNameInput);
        progressBar = findViewById(R.id.progressBar);
        btnSave = findViewById(R.id.saveButton);

        url = getString(R.string.api_url);
        Intent intent = getIntent();
        userId = (long) intent.getLongExtra("userId", 0);

        getUserInfos();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveModifications(inputEmail.getText().toString(), inputPassword.getText().toString(), inputLastname.getText().toString(), inputFirstname.getText().toString());
            }
        });
    }

    private void getUserInfos() {

    }


    private void saveModifications(String email,final String password, String lastname, String firstname){

        String finalUrl = url + "users";
        //User finalUser =
        Map<String, String> params = user.getHashMap(password);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Modifications Sauvegardées", Toast.LENGTH_LONG).show();
            }
        };

        HTTPRequestHelper.putRequest(getApplicationContext(), url, callback, getToken(), new JSONObject(params));
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

package com.example.trips.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Helpers.JSONHelper;
import com.example.trips.Models.User;
import com.example.trips.R;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
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
                String email = inputEmail.getText().toString();
                String lastname = inputLastname.getText().toString();
                String firstname = inputFirstname.getText().toString();
                String password = inputPassword.getText().toString();

                if(validateInputs(email, lastname, firstname)){
                    saveModifications(email, password, lastname, firstname);
                }
            }
        });
    }

    private void getUserInfos() {
        String finalUrl = url + "users/" + userId;

        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray response) {
                List<User> users = JSONHelper.userListFromJSONObject(response);
                user = users.get(0);
                setInputs();
            }
        };

        HTTPRequestHelper.getRequest(getApplicationContext(), finalUrl, callback, getToken());
    }

    private void setInputs() {
        inputEmail.setText(this.user.getEmail());
        inputFirstname.setText(this.user.getFirstName());
        inputLastname.setText(this.user.getLastName());
    }


    private void saveModifications(String email,final String password, String lastname, String firstname){

        String finalUrl = url + "users";
        user.setFirstName(firstname);
        user.setEmail(email);
        user.setLastName(lastname);
        Map<String, String> params = user.getHashMap("");

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Modifications Sauvegardées", Toast.LENGTH_LONG).show();
            }
        };

        HTTPRequestHelper.putRequest(getApplicationContext(), finalUrl, callback, getToken(), new JSONObject(params));
    }

    private boolean validateInputs(String email, String lastname, String firstname){

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Veuillez indiquer votre adresse mail!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(firstname)) {
            Toast.makeText(getApplicationContext(), "Veuillez indiquer votre prénom!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(lastname)) {
            Toast.makeText(getApplicationContext(), "Veuillez indiquer votre nom!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

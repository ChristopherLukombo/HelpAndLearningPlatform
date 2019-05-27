package com.example.trips.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.trips.Models.User;
import com.example.trips.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFirstname, inputLastname, inputPseudo;
    private Button btnSignup, btnLogin;
    private ProgressBar progressBar ;
    private String url;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = findViewById(R.id.email);
        inputPseudo = findViewById(R.id.pseudo);
        inputPassword = findViewById(R.id.password);
        inputFirstname = findViewById(R.id.firstname);
        inputLastname = findViewById(R.id.lastname);
        progressBar = findViewById(R.id.progressBar);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);
        this.url = "http://192.168.0.12:8080/api/";
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), LoginActivity.class));
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), LoginActivity.class));
                //register();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void register() {
        final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();
        final String lastName = inputLastname.getText().toString();
        final String firstName = inputFirstname.getText().toString();
        final String pseudo = inputPseudo.getText().toString();

        boolean inputsAreValid = validateInputs(email, password, lastName, firstName, pseudo);

        progressBar.setVisibility(View.VISIBLE);
        if(inputsAreValid) {
            register(email, password, lastName, firstName, pseudo);
        }

    }


    private boolean validateInputs(String email, String password, String lastname, String firstname, String pseudo){

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Veuillez indiquer votre adresse mail!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Veuillez indiquer votre mot de passe!", Toast.LENGTH_SHORT).show();
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

    private void register(String email, String password, String lastname, String firstname, String pseudo ){

        String url = this.url + "register";
        User user = new User(email, pseudo, firstname, lastname);
        Map<String, String> params = makeHashMap(user, password);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent( getApplicationContext(), MainActivity.class));
                        finish();
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
        requestQueue.add(stringRequest);
    }

    private Map<String, String> makeHashMap(User user, String password){
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("email", user.getEmail());
        hashMap.put("password", password);
        hashMap.put("lastName", user.getLastName());
        hashMap.put("firstName", user.getFirstName());
        hashMap.put("login", user.getPseudo());
        hashMap.put("langKey", user.getLangKey());
        hashMap.put("activated", String.valueOf(user.isActivated()));
        hashMap.put("countryOfResidence", user.getCountryOfResidence());
        hashMap.put("id", String.valueOf(user.getId()));
        hashMap.put("authorityId", String.valueOf(user.getAuthorityId()));
        hashMap.put("imageUrl", user.getImageUrl());

        return hashMap;
    }
}

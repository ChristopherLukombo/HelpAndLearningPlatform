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
import com.example.trips.Helpers.AuthenticatorHelper;
import com.example.trips.Models.User;
import com.example.trips.R;
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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), LoginActivity.class));
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
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
            sendRequest(email, password, lastName, firstName, pseudo);
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

    private void sendRequest(String email,final String password, String lastname, String firstname, final String pseudo ){

        String url = getString(R.string.api_url) + "register";
        User user = new User(email, pseudo, firstname, lastname);
        Map<String, String> params =user.getHashMap(password);

        Runnable runnable = new Runnable() {
            public void run() {
                saveUserSharedPreferences(pseudo, password);
                finish();
                startActivity(new Intent( RegisterActivity.this, LoginActivity.class));
            }

            private void saveUserSharedPreferences(String login, String password) {
                SharedPreferences prefs = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("LOGIN_PSEUDO", login);
                editor.putString("LOGIN_PWD", password);
                editor.commit();
            }
        };

        AuthenticatorHelper.register(getApplicationContext(), url, params, runnable);
    }
}

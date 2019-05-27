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

import com.example.trips.Authenticator;
import com.example.trips.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText inputLogin, inputPassword;
    private Button btnSignup, btnLogin, btnReset;
    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputLogin = findViewById(R.id.login);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        btnSignup = findViewById(R.id.btn_signup);
        btnLogin = findViewById(R.id.btn_login);
        btnReset = findViewById(R.id.btn_reset_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void attemptLogin() {
        String login = inputLogin.getText().toString();
        final String password = inputPassword.getText().toString();
        boolean inputsAreValid = validateInputs(login, password);

        //authenticate user
        if(inputsAreValid) {
            authenticate(login, password);
        }
    }

    private boolean validateInputs(String login, String password){

        if (TextUtils.isEmpty(login)) {
            Toast.makeText(getApplicationContext(), "Enter your username!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void authenticate(String login, String password){

        String url = getString(R.string.api_url) + "authenticate";
        Map<String, String> params = makeHashMap(login, password);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        };

        Authenticator.authenticate(getApplicationContext(), url, params, runnable);
    }

    private Map<String, String> makeHashMap(String login, String password){
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("password", password);
        hashMap.put("username", login);
        hashMap.put("rememberMe", "true");

        return hashMap;
    }
}

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
import com.example.trips.R;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText inputLogin, inputPassword;
    private Button btnSignup, btnLogin, btnReset;
    private ProgressBar progressBar ;
    private boolean authenticated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userIsAuthenticated();
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

    private void userIsAuthenticated() {
        SharedPreferences prefs = this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

        String login = prefs.getString("LOGIN_PSEUDO", "");
        String password = prefs.getString("LOGIN_PWD", "");

        if(!login.isEmpty() || !password.isEmpty()){
            authenticated = true;
            authenticate(login, password);
        }
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

    private void authenticate(final String login, final String password){

        String url = getString(R.string.api_url) + "authenticate";
        Map<String, String> params = makeHashMap(login, password);

        VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                if(!authenticated){

                    try {
                        this.saveUserSharedPreferences(login,password, Long.valueOf(response.getString("id_user")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }


            private void saveUserSharedPreferences(String login, String password, long userId) {
                SharedPreferences prefs = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("LOGIN_PSEUDO", login);
                editor.putString("LOGIN_PWD", password);
                editor.commit();
                prefs = getSharedPreferences("USER", Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putLong("USERID", userId);
                editor.commit();
            }
        };

        AuthenticatorHelper.authenticate(getApplicationContext(), url, params, callback);
    }

    private Map<String, String> makeHashMap(String login, String password){
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("password", password);
        hashMap.put("username", login);
        hashMap.put("rememberMe", "true");

        return hashMap;
    }
}

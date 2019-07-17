package com.example.trips.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.R;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ContactActivity extends BaseActivity {

    private TextView contactTitle;
    private EditText contactObject, contactContent;
    private Button buttonSendMessage;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contactContent = findViewById(R.id.contactContent);
        contactTitle = findViewById(R.id.contactTitle);
        contactObject = findViewById(R.id.contactObject);
        buttonSendMessage = findViewById(R.id.buttonSendMessage);
        url = getString(R.string.api_url);

        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String content = contactContent.getText().toString();
        String object = contactObject.getText().toString();
        if(!content.isEmpty() && !content.isEmpty()){
            String finalUrl = this.url + "contacts";

            VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
                @Override
                public void onResponse(JSONObject result) {
                    Toast.makeText(getApplicationContext(), "Votre message a bien été envoyé !", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
            };

            JSONObject params = makeParams(object, content);
            HTTPRequestHelper.postRequest(getApplicationContext(), finalUrl, callback, getToken(), params);
        }
        else{
            Toast.makeText(getApplicationContext(), "Vous ne pouvez pas envoyer un message sans objet ou contenu !", Toast.LENGTH_LONG).show();
        }
    }

    private JSONObject makeParams(String object, String content) {
        HashMap<String, String> params = new HashMap<>();
        params.put("information", content);
        params.put("subject", object);

        return new JSONObject(params);
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

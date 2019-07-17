package com.example.trips.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Helpers.JSONHelper;
import com.example.trips.Models.Comment;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Trick;
import com.example.trips.Models.User;
import com.example.trips.R;
import com.example.trips.VolleyJSONArrayCallback;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PopupFragment extends DialogFragment {

    private List<Comment> comments;
    private EditText newComment;
    private Button commentButton;
    private RecyclerView recyclerView;
    private PopupAdapter adapter;
    private CardView commentCardView;
    private String url;
    private Trick trick;
    private View layoutInflater;
    private long userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog);
        layoutInflater = inflater.inflate(R.layout.popup, container, false);
        url = getString(R.string.api_url);

        newComment = layoutInflater.findViewById(R.id.newComment);
        commentButton = layoutInflater.findViewById(R.id.commentButton);
        recyclerView = layoutInflater.findViewById(R.id.commentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        commentCardView = layoutInflater.findViewById(R.id.commentCardView);
        Dialog dialog = getDialog();

        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        comments = new ArrayList<>();

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });

        if(!trick.isSubscribed()){
            commentCardView.setVisibility(View.GONE);
        }

        getComments();


        return layoutInflater;
    }

    private void sendComment() {
        String content = newComment.getText().toString();
        if(!content.isEmpty()){
            String finalUrl = this.url + "comments";

            VolleyJSONObjectCallback callback = new VolleyJSONObjectCallback() {
                @Override
                public void onResponse(JSONObject response) {
                    getComments();
                    newComment.setText("");
                }
            };

            HTTPRequestHelper.postRequest(layoutInflater.getContext(), finalUrl, callback, getToken(), getCommentsParams(content));
        }
    }

    private JSONObject getCommentsParams(String content){
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("name", String.valueOf(content));
            jsonBody.put("trickId", String.valueOf(trick.getId()));
            jsonBody.put("userId", String.valueOf(userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }

    public void setData(Trick trick, long userId){
        this.trick = trick;
        this.userId = userId;
    }

    private void getComments() {
        String finalUrl = this.url + "comments/trick/" + trick.getId();

        VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback() {
            @Override
            public void onResponse(JSONArray response) {
                comments = JSONHelper.commentListFromJSONArray(response);
                getCommentsUsers(comments);
            }
        };

        HTTPRequestHelper.getRequest(layoutInflater.getContext(), finalUrl, callback, getToken());
    }

    private void getCommentsUsers(List<Comment> comments) {
        if(comments != null){
            for(final Comment comment : comments){
                String finalUrl = this.url + "users/" + comment.getUserId();

                VolleyJSONArrayCallback callback = new VolleyJSONArrayCallback() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<User> result = JSONHelper.userListFromJSONObject(response);
                        comment.setUser(result.get(0));

                        if(comments.indexOf(comment) == (comments.size() -1)){
                            setAdapter(comments);
                        }
                    }
                };

                HTTPRequestHelper.getRequest(layoutInflater.getContext(), finalUrl, callback, getToken());
            }
        }
    }

    private void setAdapter(List<Comment> comments) {
        this.adapter = new PopupAdapter(this.layoutInflater.getContext(), comments);
        recyclerView.setAdapter(this.adapter);
    }

    private String getToken(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        return token;
    }
}

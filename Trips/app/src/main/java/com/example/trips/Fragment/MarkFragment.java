package com.example.trips.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.trips.Helpers.HTTPRequestHelper;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Trick;
import com.example.trips.R;
import com.example.trips.VolleyJSONObjectCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarkFragment extends Fragment {

    private Trick trick;
    private RatingBar ratingBar;
    private View layoutInflater;
    private String url;
    private long userId;
    private String token;
    private boolean listenChanges;

    public MarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.layoutInflater = inflater.inflate(R.layout.fragment_mark, container, false);
        ratingBar = layoutInflater.findViewById(R.id.trickRating);

        url = getString(R.string.api_url);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(listenChanges){
                    rateTrick();
                }
            }
        });

        if(trick.getMark() != null){

            this.ratingBar.setRating((float) trick.getMark().getNote());
            listenChanges = true;
        }
        else{
            listenChanges = true;
        }

        return layoutInflater;
    }


    public void setData(Trick trick, String token, long userId){
        this.trick = trick;
        this.token = token;
        this.userId = userId;
    }

    private void rateTrick(){
        String finalUrl = this.url + "notations";

        VolleyJSONObjectCallback markCallback = new VolleyJSONObjectCallback() {
            @Override
            public void onResponse(JSONObject response) {
                Mark mark = null;
                try {
                    mark = new Mark(response.getDouble("note"), trick.getId(), userId);
                    mark.setId(response.getLong("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                trick.setMark(mark);
                Toast.makeText(getContext(), "Merci d'avoir noté l'astuce !", Toast.LENGTH_LONG).show();
            }

        };

        if(trick.getMark() != null){
            HTTPRequestHelper.putRequest(getContext(), finalUrl, markCallback, token, getNotationParams(ratingBar.getRating(), trick.getMark() ));
        }
        else{
            HTTPRequestHelper.postRequest(getContext(), finalUrl, markCallback, token, getNotationParams(ratingBar.getRating(), null));
        }

    }

    private JSONObject getNotationParams(double markNotation, Mark mark){
        JSONObject jsonBody = new JSONObject();

        try {
            if(mark != null){
                jsonBody.put("id", String.valueOf(trick.getMark().getId()));
            }
            jsonBody.put("note", String.valueOf(markNotation));
            jsonBody.put("trickId", String.valueOf(trick.getId()));
            jsonBody.put("userId", String.valueOf(userId));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonBody;
    }
}

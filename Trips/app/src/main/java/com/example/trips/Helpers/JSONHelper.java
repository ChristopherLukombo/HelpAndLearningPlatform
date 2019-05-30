package com.example.trips.Helpers;

import com.example.trips.Models.Category;
import com.example.trips.Models.Trick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<Trick> trickListFromJSONObject(JSONArray trickJSONArray) {
        List<Trick> tricksList = new ArrayList<>();

        for(int i = 0; i < trickJSONArray.length(); i++){
            try{
                JSONObject jsonobject = trickJSONArray.getJSONObject(i);
                long categoryId = jsonobject.getLong("categoryId");
                long userId = jsonobject.getLong("ownUserId");
                String content = jsonobject.getString("content");
                String name = jsonobject.getString("wording");
                String description = jsonobject.getString("description");
                String creationDate = jsonobject.getString("creationDate");
                long id = jsonobject.getLong("id");

                Trick trick = new Trick(categoryId, userId, creationDate, description, name,content);
                trick.setId(id);
                tricksList.add(trick);
            }
            catch (JSONException exception){

            }
        }

        return tricksList;
    }

    public static List<Category> categoryListFromJSONObject(JSONArray categoriesJSONArray) {
        List<Category> categories = new ArrayList<>();

        for(int i = 0; i < categoriesJSONArray.length(); i++){
            try{
                JSONObject jsonobject = categoriesJSONArray.getJSONObject(i);

                long categoryId = jsonobject.getLong("id");
                String name = jsonobject.getString("wording");

                Category category = new Category(name);
                category.setId(categoryId);

                categories.add(category);
            }
            catch (JSONException exception){

            }
        }

        return categories;
    }


}

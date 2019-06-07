package com.example.trips.Helpers;

import com.example.trips.Models.Category;
import com.example.trips.Models.Mark;
import com.example.trips.Models.Subscription;
import com.example.trips.Models.Trick;
import com.example.trips.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONHelper {

    public static List<Trick> trickListFromJSONObject(JSONArray trickJSONArray) {
        List<Trick> tricksList = new ArrayList<>();

        if(trickJSONArray != null) {
            for (int i = 0; i < trickJSONArray.length(); i++) {
                try {
                    JSONObject jsonobject = trickJSONArray.getJSONObject(i);
                    long categoryId = jsonobject.getLong("categoryId");
                    long userId = jsonobject.getLong("ownUserId");
                    String content = jsonobject.getString("content");
                    String name = jsonobject.getString("wording");
                    String description = jsonobject.getString("description");
                    String creationDate = jsonobject.getString("creationDate");
                    long id = jsonobject.getLong("id");

                    Trick trick = new Trick(categoryId, userId, creationDate, description, name, content);
                    trick.setId(id);
                    tricksList.add(trick);
                } catch (JSONException exception) {

                }
            }

            return tricksList;
        }

        return null;
    }

    public static List<Category> categoryListFromJSONObject(JSONArray categoriesJSONArray) {
        List<Category> categories = new ArrayList<>();

        if(categoriesJSONArray != null) {
            for (int i = 0; i < categoriesJSONArray.length(); i++) {
                try {
                    JSONObject jsonobject = categoriesJSONArray.getJSONObject(i);

                    long categoryId = jsonobject.getLong("id");
                    String name = jsonobject.getString("wording");

                    Category category = new Category(name);
                    category.setId(categoryId);

                    categories.add(category);
                } catch (JSONException exception) {

                }
            }

            return categories;
        }

        return null;
    }

    public static List<User> userListFromJSONObject(JSONArray userJSONArray) {
        List<User> usersList = new ArrayList<>();

        if(userJSONArray != null){
            for(int i = 0; i < userJSONArray.length(); i++){
                try{
                    JSONObject jsonobject = userJSONArray.getJSONObject(i);
                    int authorityId = jsonobject.getInt("authorityId");
                    boolean activated = jsonobject.getBoolean("activated");
                    String countryOfResidence = jsonobject.getString("countryOfResidence");
                    String email = jsonobject.getString("email");
                    String lastName = jsonobject.getString("lastName");
                    String firstName = jsonobject.getString("firstName");
                    String login = jsonobject.getString("login");
                    String langKey = jsonobject.getString("langKey");
                    long id = jsonobject.getLong("id");

                    User user = new User(email, login, firstName, lastName);
                    user.setId(id);
                    user.setAuthorityId(authorityId);
                    user.setCountryOfResidence(countryOfResidence);
                    user.setLangKey(langKey);
                    user.setActivated(activated);
                    usersList.add(user);
                }
                catch (JSONException exception){

                }
            }

            return usersList;
        }

        return null;

    }

    public static List<Mark> markListFromJSONObject(JSONArray markJSONArray) {
        List<Mark> marksList = new ArrayList<>();

        if(markJSONArray != null) {
            for (int i = 0; i < markJSONArray.length(); i++) {
                try {
                    JSONObject jsonobject = markJSONArray.getJSONObject(i);
                    long id = jsonobject.getLong("id");
                    double note = jsonobject.getLong("note");
                    long trickId = jsonobject.getLong("trickId");
                    long userId = jsonobject.getLong("userId");

                    Mark mark = new Mark(note, trickId, userId);
                    mark.setId(id);
                    marksList.add(mark);
                } catch (JSONException exception) {

                }
            }

            return marksList;
        }

        return null;
    }

    public static List<Subscription> subscribtionListFromJSONObject(JSONArray jsonArray) {
        List<Subscription> subscriptions = new ArrayList<>();

        if(jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);

                    long subscriptionId = jsonobject.getLong("id");
                    String subscriptionDate = jsonobject.getString("subscriptionDate");
                    long userId = jsonobject.getLong("userId");
                    long trickId = jsonobject.getLong("trickId");

                    Subscription subscription = new Subscription(subscriptionDate, userId, trickId);
                    subscription.setId(subscriptionId);

                    subscriptions.add(subscription);

                } catch (JSONException exception) {

                }
            }

            return subscriptions;
        }

        return null;
    }
}

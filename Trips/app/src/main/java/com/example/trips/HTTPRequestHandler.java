package com.example.trips;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HTTPRequestHandler {

    private String url;
    private RequestQueue queue;
    private Context context;
    private static HTTPRequestHandler httpRequestInstance;

    public HTTPRequestHandler(String url, Context context){
        if(httpRequestInstance == null){
            httpRequestInstance = this;
        }
    }

    private String buildURL(String url){
        return this.url + url;
    }

    public HTTPRequestHandler getInstance() {
        return httpRequestInstance;
    }

    public RequestQueue getRequestQueue() {
        if (queue == null){
            queue = Volley.newRequestQueue(this.context);
        }

        return queue;
    }

    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    /**
     Cancel all the requests matching with the given tag
     **/
    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

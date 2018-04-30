package com.example.nikhil.login;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import static com.example.nikhil.login.QueryUtils.LOG_TAG;

/**
 * Created by nikhil on 27/4/18.
 */

public class CustomLoader extends AsyncTaskLoader<String> {

    private String urls;
    private String[] input;

    CustomLoader(Context context,String urls,String[] input){
        super(context);
        this.urls = urls;
        this.input = input;
        Log.v(LOG_TAG, urls);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if(urls == null){
            Log.v(LOG_TAG, "url is null");
            return null;
        }

        Log.v(LOG_TAG, urls);
        String result = QueryUtils.fetchData(urls,input);
        return result;

    }
}

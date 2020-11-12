package com.example.trashcollector;

import android.app.Application;
import android.content.Context;

public class WastCollectorApp extends Application {

    public static final String TAG = WastCollectorApp.class
            .getSimpleName();
    private static WastCollectorApp mInstance;
    /*private RequestQueue mRequestQueue;*/

    public WastCollectorApp() {
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }

    // private Channelize channelize;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        //  MultiDex.install(this);
    }

    public static synchronized WastCollectorApp getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }

}



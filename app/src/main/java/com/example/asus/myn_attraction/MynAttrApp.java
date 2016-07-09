package com.example.asus.myn_attraction;

import android.app.Application;
import android.content.Context;

/**
 * Created by Asus on 7/6/2016.
 */
public class MynAttrApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
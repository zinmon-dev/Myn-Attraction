package com.example.asus.myn_attraction.utils;

import com.google.gson.Gson;


public class CommonInstances {

    private static Gson gson = new Gson();

    public static Gson getGsonInstance() {
        return gson;
    }
}

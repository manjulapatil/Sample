package com.example.sample.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by manjula on 9/21/16.
 */
public class UserWrapper {
    public static List<User> fromJson(String s) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type collectionType = new TypeToken<List<User>>() {
        }.getType();
        return gson.fromJson(s, collectionType);
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}

package com.example.ritu.c_ma;

/**
 * Created by ritu on 3/10/18.
 */
import android.app.Activity;
import android.content.SharedPreferences;
public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    // Sydney as the default city
    String getCity(){
        return prefs.getString("city", "New Delhi, India");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}
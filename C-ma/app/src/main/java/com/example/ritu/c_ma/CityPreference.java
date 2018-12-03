package com.example.ritu.c_ma;

/**
 * Created by ritu on 3/10/18.
 */
import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CityPreference {

    SharedPreferences prefs;
    GoogleApiClient mGoogleApiClient;

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

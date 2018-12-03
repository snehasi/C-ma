package com.example.ritu.c_ma;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements LocationListener{
    private LocationManager locationManager;
    private double longitude;
    private double latitude;
    public String city;
    public String country;
    public String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return ;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
    }


    @Override
    public void onLocationChanged(Location location){
        longitude = location.getLongitude();
        latitude = location.getLatitude();


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle){

    }

    @Override
    public void onProviderEnabled(String s){

    }

    @Override
    public void onProviderDisabled(String s){

    }


    public String loc_function(Context context){
        try{
            Geocoder geocoder = new Geocoder(context);
            List<Address> address = null;
            address = geocoder.getFromLocation(latitude,longitude,1);

            city = address.get(0).getLocality();
            country = address.get(0).getCountryCode();
            String state = address.get(0).getAdminArea();
            x = city + ", " + country;
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constants.PREF_KEY.user_city,x);
            editor.commit();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        //startActivity(new Intent(LocationActivity.this,WeatherActivity.class));
        return x;

    }


}

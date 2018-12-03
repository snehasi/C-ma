package com.donut.cma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CitySelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
    }

    public void submitCityName(View view)
    {
        TextView cityTextView = (TextView) findViewById(R.id.cityTextView);

        String cityName = cityTextView.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.PREF_KEY.user_city, cityName);

        editor.commit();

        if (!Constants.DEBUG)
        {
            editor.putBoolean(Constants.PREF_KEY.setup_done, true);

            editor.commit();
        }

        Intent intent = new Intent(CitySelectActivity.this, HomeActivity.class);

        startActivity(intent);

    }
}

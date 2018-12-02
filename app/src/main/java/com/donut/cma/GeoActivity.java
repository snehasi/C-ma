package com.donut.cma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GeoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new GeoFragment())
                    .commit();
        }
    }
}

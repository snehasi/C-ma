package com.donut.cma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.areaContainer, AreaFragment.newInstance())
                    .commitNow();
        }
    }
}

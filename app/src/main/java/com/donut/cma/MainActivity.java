package com.donut.cma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences = this.getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
        

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (!sharedPreferences.getBoolean(Constants.PREF_KEY.setup_done, false) || Constants.DEBUG) {
                    intent = new Intent(MainActivity.this, WelcomeActivity.class);
                }
                else
                {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                }

                intent.setFlags(intent.getFlags());

                startActivity(intent);
            }
        }, Constants.SPLASH_TIMEOUT);
    }
}

package com.example.ritu.c_ma;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onSetupClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(Constants.PREF_KEY.setup_done, true);

        editor.commit();

        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}

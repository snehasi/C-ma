package com.donut.cma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WelCropActivity extends AppCompatActivity {
    public ListView listView;
    public ArrayAdapter<String> adapter;
    public SharedPreferences sharedPreferences;
    public EditText cropSearch;
    public String[] crops = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_crop);


        new APIGet().execute(Constants.CROPS_URL);

        if (crops == null)
        {
            crops = getResources().getStringArray(R.array.crops_array);
        }

        for (int i = 0; i < crops.length; i++)
        {
            Log.e("Crops", crops[i]);
        }

        listView = (ListView) findViewById(R.id.welcomeCropList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, crops);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        sharedPreferences = getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);

        Set<String> selected_crops = sharedPreferences.getStringSet(Constants.PREF_KEY.user_crops, new HashSet<String>());

        if (selected_crops != null)
        {
            for (int i = 0; i < crops.length; i++)
                if (selected_crops.contains(crops[i])) {
                    listView.setItemChecked(i, true);
                }
        }

        cropSearch = (EditText) findViewById(R.id.cropSearch);

        cropSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                WelCropActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onChooseClick(View view)
    {
        SparseBooleanArray checked_positions = listView.getCheckedItemPositions();
        ArrayList<String> checked_crops = new ArrayList<>();

        for (int i = 0; i < checked_positions.size(); i++)
        {
            int position = checked_positions.keyAt(i);

            if (checked_positions.valueAt(i))
            {
                checked_crops.add(adapter.getItem(position));
            }
        }

        Set <String> checked_set = new HashSet<>();
        checked_set.addAll(checked_crops);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putStringSet(Constants.PREF_KEY.user_crops, checked_set);

        editor.commit();

        if (Constants.DEBUG)
        {
            Toast.makeText(this, "Crops saved", Toast.LENGTH_SHORT).show();
        }

        Intent intent;
        if (Constants.CITY_SELECT_PLACES)
        {
            intent = new Intent (this, GeoActivity.class);
        }
        else
        {
            intent = new Intent (this, CitySelectActivity.class);
        }

        startActivity(intent);
    }

    public void setupComplete() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(Constants.PREF_KEY.setup_done, true);

        editor.commit();

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    class APIGet extends AsyncTask<String, Void, ArrayList<String> > {

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String address = strings[0];

            URL url = null;
            try {
                url = new URL(address);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            BufferedReader rd = null;
            ArrayList<String> result = new ArrayList<>();
            try {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.add(line.replaceAll("\n", ""));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }



            return result;
            //.toArray();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            adapter = new ArrayAdapter<String>(WelCropActivity.this, android.R.layout.simple_list_item_multiple_choice, strings);
            listView.setAdapter(adapter);
        }
    }
}

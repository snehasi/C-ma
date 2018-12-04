package com.donut.cma;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AreaFragment extends Fragment implements LocationListener,View.OnClickListener {

    public LocationManager mLocationManager;

    Button Start;
    Button Stop;
    Boolean Recording;
    TextView Locations;
    int[][] LocationPoints;

    public static AreaFragment newInstance() {return new AreaFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_fragment, container,false);

        Button Start=rootView.findViewById(R.id.AreaStart);
        Button Stop=rootView.findViewById(R.id.AreaStop);
        Stop.setVisibility(View.INVISIBLE);
        return rootView;
    }

    @Override
    public void onLocationChanged(Location location) {
        //TextView t=
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onClick(View view) {
        String s= view.toString();
        if(s.contains("Start")){
            Start.setVisibility(View.INVISIBLE);
            Stop.setVisibility(View.VISIBLE);
            Recording=true;
            Toast.makeText(getContext(),"Started Recording",Toast.LENGTH_SHORT).show();
        }
        else{
            Stop.setVisibility(View.INVISIBLE);
            Start.setVisibility(View.VISIBLE);
            Recording=false;
            Toast.makeText(getContext(),"Stopped Recording",Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.ahmad.marketprices;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AreaFragment extends Fragment implements LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    public LocationManager mLocationManager;

    TextView LocationsTextView;
    TextView DynamicValues;
    View RootView;
    ArrayList<LocationPoints> UserLocationPoints;
    Double X;
    Double Y;
    boolean flag=true;

    public static AreaFragment newInstance() {
        return new AreaFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UserLocationPoints=new ArrayList<LocationPoints>();
        final View RootView = inflater.inflate(R.layout.area_fragment, container, false);
        //Stop=RootView.findViewById(R.id.AreaStop);
        DynamicValues=RootView.findViewById(R.id.AreaDynamic);
        LocationsTextView = RootView.findViewById(R.id.AreaOldValues);
        TextView GPS = RootView.findViewById(R.id.AreaGPS);
        GPS.setText("GPS Values :-\nLatitude\nLongitude");

        Button button1 = RootView.findViewById(R.id.AreaGetLocation);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions((Activity) getContext(),
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                }
                Location l=mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                X=l.getLatitude();
                Y=l.getLongitude();
                UserLocationPoints.add(new LocationPoints(X,Y));
                if(flag){
                    LocationsTextView.setText(X + " " + Y);
                    flag=false;
                }
                else {
                    LocationsTextView.setText(X + " " + Y + "\n" + LocationsTextView.getText());
                }
            }
        });

        Button button2 = RootView.findViewById(R.id.AreaCalculate);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TextView t=RootView.findViewById(R.id.AreaCalculatedValue);
                findMyArea(t);
            }
        });

        return RootView;
    }

    public double areafinder(double[][] arr)
    {
        int n = arr.length;
        /** copy initial point to last row **/
        arr[n - 1][0] = arr[0][0];
        arr[n - 1][1] = arr[0][1];
        double det = 0.0;
        /** add product of x coordinate of ith point with y coordinate of (i + 1)th point **/
        for (int i = 0; i < n - 1; i++)
            det += (double)(arr[i][0] * arr[i + 1][1]);
        /** subtract product of y coordinate of ith point with x coordinate of (i + 1)th point **/
        for (int i = 0; i < n - 1; i++)
            det -= (double)(arr[i][1] * arr[i + 1][0]);
        /** find absolute value and divide by 2 **/
        det = Math.abs(det);
        det /= 2;
        return det;
    }

    public void findMyArea(TextView t){
        double[][] arr=new double[UserLocationPoints.size()+1][2];
        for(int i=0;i<UserLocationPoints.size();i++){
            arr[i][0]=UserLocationPoints.get(i).X;
            arr[i][1]=UserLocationPoints.get(i).Y;
        }
        t.setText(""+areafinder(arr));
    }
    @Override
    public void onResume() {
        super.onResume();
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            LocationsTextView.setText("Permission Disabled");
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onPause(){
        mLocationManager.removeUpdates(this);
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {

        DynamicValues.setText("\n"+location.getLongitude()+"\n"+location.getLatitude());
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

}

class LocationPoints{
    double X;
    double Y;
    LocationPoints(double x,double y){
        X=x;
        Y=y;
    }
}
package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import static android.content.Context.LOCATION_SERVICE;


public class gpstrack implements LocationListener {
    Context context;
    private boolean canGetLocation;

    public gpstrack(Context c) {
        context = c;
    }

    public Location getLocation() {
        Location location=null;
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(ContextCompat.checkSelfPermission(context , android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(context,"Access Denied",Toast.LENGTH_SHORT).show();
                return null;
            }
            if (!isGPSEnabled && !isNetworkEnabled) {
              return null;
            }
            else {
                this.canGetLocation = true;
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                2000,
                                10, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            }
                        }
                }

            }
        catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
};
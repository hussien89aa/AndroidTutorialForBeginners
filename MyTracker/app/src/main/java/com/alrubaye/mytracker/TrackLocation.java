package com.alrubaye.mytracker;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by hussienalrubaye on 9/26/16.
 */

public class TrackLocation implements LocationListener {

    public static Location location;
    public  static boolean isRunning=false;
    public  TrackLocation(){
        isRunning=true;
        location=new Location("not defined");
        location.setLatitude(0);
        location.setLongitude(0);
    }
    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
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
}

package com.alrubaye.mytracker;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hussienalrubaye on 9/26/16.
 */

public class MyServie extends Service {
     public static boolean IsRunning=false;
    DatabaseReference databaseReference;
    public  static Location location;
    TrackLocation trackLocation;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        IsRunning=true;
        databaseReference= FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        GlobalInfo globalInfo= new GlobalInfo(this);
        globalInfo.LoadData();
               trackLocation = new TrackLocation();
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, trackLocation);

        databaseReference.child("Users").child(GlobalInfo.PhoneNumber).
                child("Updates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (location==null)return;
                databaseReference.child("Users").
                        child(GlobalInfo.PhoneNumber).child("Location").child("lat")
                        .setValue( location.getLatitude());

                databaseReference.child("Users").
                        child(GlobalInfo.PhoneNumber).child("Location").child("lag")
                        .setValue( location.getLongitude());

                DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:MM:ss");
                Date date= new Date();
                databaseReference.child("Users").
                        child(GlobalInfo.PhoneNumber).child("Location").
                        child("LastOnlineDate")
                        .setValue(df.format(date).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return START_NOT_STICKY;
    }

    public class TrackLocation implements LocationListener {


        public    boolean isRunning=false;
        public  TrackLocation(){
            isRunning=true;
            location=new Location("not defined");
            location.setLatitude(0);
            location.setLongitude(0);
        }
        @Override
        public void onLocationChanged(Location location) {
            MyServie.location=location;
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
}

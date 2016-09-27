package com.alrubaye.mytracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by hussienalrubaye on 9/26/16.
 */

public class RunithOS extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            GlobalInfo globalInfo= new GlobalInfo(context);
            globalInfo.LoadData();
            //start location track
            if (!TrackLocation.isRunning){
                TrackLocation trackLocation = new TrackLocation();
                LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, trackLocation);
            }
            if (!MyServie.IsRunning){
                Intent  intent1=new Intent(context,MyServie.class);
                context.startService(intent1);
            }
        }
    }
}

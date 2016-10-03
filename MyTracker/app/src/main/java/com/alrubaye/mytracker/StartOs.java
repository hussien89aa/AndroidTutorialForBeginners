package com.alrubaye.mytracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by hussienalrubaye on 10/3/16.
 */

public class StartOs extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){


            /// only when system start
            Intent intentService=new Intent(context,MyServie.class);
            context. startService(intentService);
        }
    }
}

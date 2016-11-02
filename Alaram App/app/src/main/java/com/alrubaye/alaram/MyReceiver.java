package com.alrubaye.alaram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase("com.alraby.alam")){
            Bundle b=intent.getExtras();
            Toast.makeText(context,b.getString("MyMessage"),Toast.LENGTH_LONG).show();
        }
        else if (intent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED")){
            // restart
            savedata savedata =new savedata(context);
            savedata.LoadData();


        }
    }
}

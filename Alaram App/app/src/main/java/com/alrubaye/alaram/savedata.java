package com.alrubaye.alaram;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by hussienalrubaye on 11/1/16.
 */

public class savedata {
    SharedPreferences ShredRef;
    Context context;
    public savedata(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    public  void SaveData(int hour,int minute){
        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putInt("hour",hour);
        editor.putInt("minute",minute);
        editor.commit();
    }

    public void LoadData(){
        int Minute=ShredRef.getInt("minute",0);
        int Hour=ShredRef.getInt("hour",0);
        Alarmset(Hour,Minute);

    }

    void Alarmset(int Hour,int Minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Minute);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager am = (AlarmManager)  context.getSystemService  (Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        intent.setAction("com.alraby.alam");
        intent.putExtra("MyMessage","hello from alarm");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pi);
    }
}

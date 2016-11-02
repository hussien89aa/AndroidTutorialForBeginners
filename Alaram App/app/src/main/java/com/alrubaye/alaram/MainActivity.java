package com.alrubaye.alaram;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.app.FragmentManager fm= getFragmentManager();
        PopTime popTime=new PopTime();
        popTime.show(fm,"Show fragment");
    }

    void SetTime(int Hour ,int Minute ){


       // /save dat
        savedata savedata =new savedata(this);
        savedata.Alarmset(Hour,Minute);
        savedata.SaveData(Hour,Minute);

    }
}

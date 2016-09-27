package com.alrubaye.mytracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hussienalrubaye on 9/26/16.
 */

public class GlobalInfo {
    public  static String PhoneNumber="";
    public  static Map<String,String> MyTrackers=new HashMap<>();

    public  static  void UpdatesInfo(String UserPhone){
        DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:MM:ss");
        Date date= new Date();
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(UserPhone).
                child("Updates").setValue(df.format(date).toString());
    }

    public static String FormatPhoneNumber(String Oldnmber){
        try{
            String numberOnly= Oldnmber.replaceAll("[^0-9]", "");
            if(Oldnmber.charAt(0)=='+') numberOnly="+" +numberOnly ;
            if (numberOnly.length()>=10)
                numberOnly=numberOnly.substring(numberOnly.length()-10,numberOnly.length());
            return(numberOnly);
        }
        catch (Exception ex){
            return(" ");
        }
    }

    Context context;
    SharedPreferences ShredRef;
    public  GlobalInfo(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences("myRef",Context.MODE_PRIVATE);
    }

    void SaveData(){
        String MyTrackersList="" ;
        for (Map.Entry  m:GlobalInfo.MyTrackers.entrySet()){
            if (MyTrackersList.length()==0)
                MyTrackersList=m.getKey() + "%" + m.getValue();
            else
                MyTrackersList+=  "%"+m.getKey() + "%" + m.getValue();

        }

        if (MyTrackersList.length()==0)
            MyTrackersList="empty";


        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString("MyTrackers",MyTrackersList);
        editor.putString("PhoneNumber",PhoneNumber);
        editor.commit();
    }

    void LoadData(){
        MyTrackers.clear();
        PhoneNumber= ShredRef.getString("PhoneNumber","empty");
        String MyTrackersList= ShredRef.getString("MyTrackers","empty");
        if (!MyTrackersList.equals("empty")){
            String[] users=MyTrackersList.split("%");
            for (int i=0;i<users.length;i=i+2){
                MyTrackers.put(users[i],users[i+1]);
            }
        }


        if (PhoneNumber.equals("empty")){

            Intent intent=new Intent(context, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }


}

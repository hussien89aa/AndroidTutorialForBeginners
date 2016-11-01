
 public void startAlert() {  
       // for Alarm 25/12/2012 at 12.00   
Calendar myAlarmDate = Calendar.getInstance();
myAlarmDate.setTimeInMillis(System.currentTimeMillis());
myAlarmDate.set(2012, 11, 25, 12, 00, 0);
//other way
  Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Minute);
        calendar.set(Calendar.SECOND, 0);

 //define Repeating Alarm Start After Each 2 Minutes
 

  AlarmManager am = (AlarmManager)context.getSystemService  (Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("com.quranonline.Broadcast");
        intent.putExtra("MyMessage",context.getResources().getString(R.string.msg_notify));
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY , pi);
// Create one time   Alarm Start After Each 2 Minutes
alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(),_myPendingIntent); 

 
    }  


    /* Permission need
       <uses-permission android:name="android.permission.VIBRATE" />  


    */

       /* run with os
       //permission
           <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
            
            //filters
             <action android:name="android.intent.action.BOOT_COMPLETED" />
                 <action android:name="android.intent.action.ACTION_SHUTDOWN" />
                <action android:name="android.intent.action.QUICKBOOT_POWEROFF" />


       */
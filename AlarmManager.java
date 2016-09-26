
 public void startAlert() {  
       // for Alarm 25/12/2012 at 12.00   
Calendar myAlarmDate = Calendar.getInstance();
myAlarmDate.setTimeInMillis(System.currentTimeMillis());
myAlarmDate.set(2012, 11, 25, 12, 00, 0);

 AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);  
      
 Intent intent = new Intent(this, MyBroadcastReceiver.class); 
intent.putExtra("MyMessage","HERE I AM PASSING THEPERTICULAR 
        	MESSAGE WHICH SHOULD BE SHOW ON RECEIVER OF ALARM"); 
PendingIntent pendingIntent = PendingIntent.getBroadcast(  
                                      this.getApplicationContext(),
                                       234324243, intent, 0);   
alarmManager.set(AlarmManager.RTC_WAKEUP,
 myAlarmDate.getTimeInMillis(),_myPendingIntent); 
/*  Create Repeating Alarm Start After Each 2 Minutes
    am.setRepeating(AlarmManager.ELAPSED_REALTIME,myAlarmDate.getTimeInMillis(),
                  2*60*60,pendingIntent);
 */
    }  


    /* Permission need
       <uses-permission android:name="android.permission.VIBRATE" />  


    */
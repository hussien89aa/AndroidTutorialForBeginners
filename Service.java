 

/**
 * Created by hussienalrubaye on 10/1/16.
 */

public class LocationService extends Service
{
   
    @Override
    public void onCreate()
    {
        super.onCreate();
      
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
       

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }






    @Override
    public void onDestroy() {
        // handler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
       // Log.v("STOP_SERVICE", "DONE");
      
    }




 

}

//Start service

            Intent callReceiverIntent = new Intent("com.domain.name.servicename");
            callReceiverIntent.putExtras(new Intent(context.getApplicationContext(), LocationService.class));
           context. startService(callReceiverIntent);

//
           //Stop service

            Intent callReceiverIntent = new Intent("com.domain.name.servicename");
          callReceiverIntent.putExtras(new Intent(context.getApplicationContext(), LocationService.class));
           context. stopService(callReceiverIntent);

//Config service Mainfest.xml
           /*

            <service android:name=".LocationService" >
            <intent-filter>
                <action android:name="com.domain.name.servicename" />
            </intent-filter>
            </service>

            */

            //start alert




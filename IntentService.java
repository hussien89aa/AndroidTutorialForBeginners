
//Define Service
public class ServiceNotification extends IntentService {
   public static boolean ServiceIsRun=false;

    public ServiceNotification() {
        super("MyWebRequestService");
    }
    protected void onHandleIntent(Intent workIntent) {

        // continue sending the messages
        while ( ServiceIsRun) {
             // creat new intent
            Intent intent = new Intent();
            //set the action that will receive our broadcast
            intent.setAction("com.example.Broadcast");
            // add data to the bundle
            intent.putExtra("username", "alxs1aa");
            // send the data to broadcast
            sendBroadcast(intent);
            //delay for 50000ms
            try{
                Thread.sleep(50000);
            }catch (Exception ex){}


        }
    }


}
//Define BroadcastReceiver

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // get the bundles in the message
        final Bundle bundle = intent.getExtras();
        // check the action equal to the action we fire in broadcast,
        if   (   intent.getAction().equalsIgnoreCase("com.example.Broadcast"))
            //read the data from the intent
            Toast.makeText(context,bundle.getString("username"),Toast.LENGTH_LONG).show();
    }
}

/* add to AndroidManifest.xml

 <!-- register the broadcast to listen to action names com.example.Broadcast-->
        <receiver android:name=".MyReceiver" android:priority="2147483647" >
            <intent-filter>
                <action android:name="com.example.Broadcast" >
                </action>
            </intent-filter>
        </receiver>
 <!-- register the service-->
        <service
            android:name=".ServiceNotification"
            android:exported="false" >
        </service>



*/

//Start Service
   // check if the services is already runs in background
        if(ServiceNotification.ServiceIsRun==false ) {
            ServiceNotification.ServiceIsRun  = true;
            //register the services to run in background
            Intent intent = new Intent(this, ServiceNotification.class);
            // start the services
            startService(intent);

        } 

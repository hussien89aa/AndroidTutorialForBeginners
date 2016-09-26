
 
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

  if   (   intent.getAction().equalsIgnoreCase("android.provider.Telephony.SMS_RECEIVED"))
{
	  if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdusObj.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    }
                    else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    }
                   // SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String senderNum =messages[i].getOriginatingAddress();
                     String message =messages[i].getMessageBody();//
                        }

    }
}

/* add to AndroidManifest.xml
   <uses-permission android:name="android.permission.READ_SMS" />


   <receiver android:name=".MyReceiver"
           android:permission="android.permission.BROADCAST_SMS"
           tools:ignore="UnprotectedSMSBroadcastReceiver">
           <intent-filter android:priority="2147483647">
               <action android:name="android.provider.Telephony.SMS_RECEIVED" />
                  <action android:name="com.example.Broadcast" >
                </action>
               <category android:name="android.intent.category.DEFAULT" />
           </intent-filter>
       </receiver>


*/

// send sendBroadcast
         Intent intent = new Intent();
            //set the action that will receive our broadcast
            intent.setAction("com.example.Broadcast");
            // add data to the bundle
            intent.putExtra("username", "alxs1aa");
            // send the data to broadcast
            sendBroadcast(intent);

//send sms
  SmsManager smsManagersend = SmsManager.getDefault();
 smsManagersend.sendTextMessage("phone number", null,"hellor from android", null, null);
                
//start activity from 
   //start activity
    Intent i = new Intent(context,MainActivity.class);
   // i.setClassName("com.test", "com.test.MainActivity");
    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(i);

  

  public class GooglePlayServices
            implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener 
    {
            private final String TAG = "GooglePlayServices:";
        protected GoogleApiClient mGoogleApiClient;
         
        Context context;
        public GooglePlayServices(  Context context) {
            this.context=context;
           
            // build the Play Services client object
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
         
       
        }

 @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: Connecting to Google Play Services");

        // Connect to Play Services
        GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
        int resultCode = gAPI.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            gAPI.getErrorDialog(this, resultCode, 1).show();
        }
        else {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            Log.i(TAG, "onStop: Disconnecting from Google Play Services");
            mGoogleApiClient.disconnect();
        }
    }
        /**
         * Google Play Services Lifecycle methods
         */
        @Override
        public void onConnected(Bundle connectionHint) {
         Log.i(TAG, "onConnected: Is connected to Google Play Services");
           //TODO: we connected
 
        }

        @Override
        public void onConnectionFailed(ConnectionResult result) {
            //error result
            Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        }

        @Override
        public void onConnectionSuspended(int cause) {
            Log.d(TAG, "Connection was suspended for some reason");
            mGoogleApiClient.connect(); //reconnected
        }
 
 

    }

    //TODO: Add play services in Grade
    //compile 'com.google.android.gms:play-services:Version'
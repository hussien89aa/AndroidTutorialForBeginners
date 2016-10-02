  public class MyLocationListener
            implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener
    {
        private final String TAG = "LOC_RECURRING_SAMPLE";

        // Constants that define how often location updates will be delivered
        private final long LOC_UPDATE_INTERVAL = 10000; // 10s in milliseconds
        private final long LOC_FASTEST_UPDATE = 5000; // 5s in milliseconds

        protected GoogleApiClient mGoogleApiClient;
        protected LocationRequest mLocRequest;
        public     Location mCurLocation;

        Context context;
        public MyLocationListener(  Context context) {
            this.context=context;
            mCurLocation = null;

            // build the Play Services client object
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mLocRequest=new LocationRequest();
            mLocRequest.setInterval(LOC_UPDATE_INTERVAL);
            mLocRequest.setFastestInterval(LOC_FASTEST_UPDATE);
            mLocRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            // TODO: create the LocationRequest we'll use for location updates
            onStart();
        }


        public void startLocationUpdates() {
            // TODO: start the location updates

            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient,mLocRequest,this);


        }

        public void stopLocationUpdates() {
            // TODO: stop the updates

            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);


        }

        protected void updateUI() {
            // take the lat and long of the current location object and add it to the list
            if (mCurLocation != null) {
                String lat = String.format("Lat: %f\n", mCurLocation.getLatitude());
                String lon = String.format("Lon: %f\n", mCurLocation.getLongitude());


            }
        }

        protected void initializeUI() {
            // start by getting the last known location as a starting point
            if (mCurLocation == null) {
                mCurLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                updateUI();


            }


        }

        /**
         * Called to handle the button clicks in the view
         */

        public void onClick(int id ) {
            switch (id){
                case 1:
                    startLocationUpdates();
                    break;
                case 0:
                    stopLocationUpdates();
                    break;
            }
        }

        /**
         * Called by Play Services when the user's location changes
         */
        @Override
        public void onLocationChanged(Location loc) {
            mCurLocation = loc;
            LocationService.location=loc;
            updateUI();
        }






        /**
         * Google Play Services Lifecycle methods
         */
        @Override
        public void onConnected(Bundle connectionHint) {

            initializeUI();
            startLocationUpdates();
        }

        @Override
        public void onConnectionFailed(ConnectionResult result) {
            Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        }

        @Override
        public void onConnectionSuspended(int cause) {
            Log.d(TAG, "Connection was suspended for some reason");
            mGoogleApiClient.connect();
        }

        /**
         * Activity lifecycle events
         */
        public  void onStart() {

            mGoogleApiClient.connect();
        }

        public void onStop() {
            mGoogleApiClient.disconnect();

        }

    }
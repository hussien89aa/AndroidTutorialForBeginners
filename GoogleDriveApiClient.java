 


public class GoogleDriveApiClient extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{
        	

			public GoogleApiClient mGoogleApiClient;
			public static final int REQUEST_DRIVE_SIGNIN = 123;

        	protected void onCreate(Bundle savedInstanceState) {
        		super.onCreate(savedInstanceState);


        		//create a api client to connect to drive
        		mGoogleApiClient =   new GoogleApiClient.Builder(this)
                					.addApi(Drive.API)
					                .addScope(Drive.SCOPE_FILE)
					                .addConnectionCallbacks(this)
					                .addScope(Drive.SCOPE_APPFOLDER)
					                .addOnConnectionFailedListener(this)
					                .build();

				//call to connect
				mGoogleApiClient.connect();


        	}

        	 @Override
		    public void onConnected(@Nullable Bundle bundle) {
		    		//implement the code after connecting
		    }

		    @Override
		    public void onConnectionSuspended(int i) {
		        if(mGoogleApiClient!=null){
		            mGoogleApiClient.disconnect();
		        }

		    }

		    @Override
		    public void onConnectionFailed(@NonNull ConnectionResult result) {
		        if (!result.hasResolution()) {
		            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
		            return;
		        }
		        try {
		        	//call for connection again
		            result.startResolutionForResult(this, REQUEST_DRIVE_SIGNIN);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    @Override
		    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
		        switch (requestCode) {

		            case REQUEST_DRIVE_SIGNIN:

		                if (resultCode == Activity.RESULT_OK) {

		                    mGoogleApiClient.connect();

		                }
		                else if (resultCode == RESULT_CANCELED){

		                    Log.d("TAG","result cancelled");
		                    return ;
		                }
		                break;
		            }
		    }

		     @Override
		    protected void onPause() {
		        super.onPause();
		    }


		    @Override
		    protected void onResume() {

		        super.onResume();
		        if (mGoogleApiClient == null) {
		            mGoogleApiClient = new GoogleApiClient.Builder(this)
		                    .addApi(Drive.API)
		                    .addScope(Drive.SCOPE_FILE)
		                    .addConnectionCallbacks(this)
		                    .addOnConnectionFailedListener(this)
		                    .build();
		            mGoogleApiClient.connect();
		        }

		    }

		    @Override
		    protected void onDestroy() {
		        super.onDestroy();
		        if ((mGoogleApiClient != null)&& (mGoogleApiClient.isConnected())){

		            mGoogleApiClient.clearDefaultAccountAndReconnect();
		            mGoogleApiClient.disconnect();
		        }
		    }
}
 
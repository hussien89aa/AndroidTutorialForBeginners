package com.hussein.mycurrentlocation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        CheckUserPermsions();

        LoadPockemon();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        LocationListner();// init the contact list

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationListner();// init the contact list
                } else {
                    // Permission Denied
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    void  LocationListner(){
        LocationListener locationListener = new MyLocationListener(this);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3, locationListener);


        myThread m_thread= new myThread();
        m_thread.start();
    }

    Location oldLocation;
    class myThread extends Thread{
        myThread(){
            oldLocation= new Location("Start");
            oldLocation.setLatitude(0);
            oldLocation.setLongitude(0);
        }
        public void run(){
            while (true) try {
                //Thread.sleep(1000);
                if (oldLocation.distanceTo(MyLocationListener.location) == 0) {
                    continue;
                }
                oldLocation = MyLocationListener.location;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        mMap.clear();

                        // Add a marker in Sydney and move the camera
                        LatLng sydney = new LatLng(MyLocationListener.location.getLatitude(), MyLocationListener.location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Me").icon(
                                BitmapDescriptorFactory.fromResource(R.drawable.mario)
                        ));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));

                        for (int i = 0; i < ListPockemons.size(); i++) {
                            Pockemon pockemon = ListPockemons.get(i);

                            if (pockemon.isCatch == false) {
                                LatLng pockemonlocation =
                                        new LatLng(pockemon.location.getLatitude(), pockemon.location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(pockemonlocation)
                                        .title(pockemon.name)
                                        .snippet(pockemon.des + ",power:" + pockemon.power)
                                        .icon(BitmapDescriptorFactory.fromResource(pockemon.Image)
                                        ));

                                // catch the pockemn
                                if (MyLocationListener.location.distanceTo(pockemon.location) < 2) {
                                    MyPower = MyPower + pockemon.power;
                                    Toast.makeText(MapsActivity.this, "Catch Pockemon, new power is" + MyPower,
                                            Toast.LENGTH_SHORT).show();
                                    pockemon.isCatch = true;
                                    ListPockemons.set(i, pockemon);

                                }
                            }


                        }

                    }
                });


            } catch (Exception ex) {
            }
        }
    }

    double MyPower=0;
    //Add list f pockemon
    ArrayList<Pockemon> ListPockemons= new ArrayList<>();
    void LoadPockemon(){

        ListPockemons.add(new Pockemon(  R.drawable.charmander, "Charmander", "Charmander living in japan", 55, 37.7789994893035, -122.401846647263));
        ListPockemons.add(new Pockemon(  R.drawable.bulbasaur ,"Bulbasaur",  "Bulbasaur living in usa", 90.5, 37.7949568502667,  -122.410494089127));
        ListPockemons.add(new Pockemon(  R.drawable.squirtle, "Squirtle",  "Squirtle living in iraq", 33.5, 37.7816621152613,  -122.41225361824  ));


    }
}

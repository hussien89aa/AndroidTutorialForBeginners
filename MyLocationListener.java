import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;


public class MyLocationListener implements LocationListener {
  Context context;
     public static Location location;
     public   MyLocationListener(Context context){
       this.context  =context ;
     }
    public void onLocationChanged(Location location) {
 this.location=location;
    }

    public void onStatusChanged(String s, int i, Bundle b) {
      //  Toast.makeText(context, "Provider status changed",
       //         Toast.LENGTH_LONG).show();
    }

    public void onProviderDisabled(String s) {
        Toast.makeText(context,
                "  GPS turned off . you cannot follow your locations",
                Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String s) {
       // SettingSaved.LoadData(context);
        Toast.makeText(context,
                " GPS turned on. you can follow your locations",
                Toast.LENGTH_LONG).show();
    }


}

LocationListener locationListener = new MyLocationListener();
LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 35000, 10, this.locationListener);

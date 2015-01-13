package com.example.anthonyluu.parkingapp;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ShowParkingActivity extends Activity {

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i("LocationStatus", "Location Changed: " + location.toString());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("LocationStatus", "Status Changed: " + status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i("LocationStatus", "Provider enabled ");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i("LocationStatus", "Provider disabled ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LatLng myLocationLatLng;
        LatLng parkingLatLng;
        GoogleMap map;

        setContentView(R.layout.activity_show_parking);

        // getting my current location
        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 500.0f, mLocationListener);
        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        double myLatitude = location.getLatitude();
        double myLongitude = location.getLongitude();
        myLocationLatLng = new LatLng(myLatitude, myLongitude);

        // getting Parcelable from intent extras
        // the ParkingItem is stored in Parcelable with all the necessary fields
        Bundle data = getIntent().getExtras();
        ParkingItemParcelable parkingItemParcelable = data.getParcelable("EXTRA_PARKING_ITEM");
        Double lat = parkingItemParcelable.getLat();
        Double lng = parkingItemParcelable.getLng();
        parkingLatLng = new LatLng(lat, lng);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        if (map!=null){
            map.addMarker(new MarkerOptions().position(parkingLatLng)
                    .title(parkingItemParcelable.getAddress()));

            map.addMarker(new MarkerOptions().position(myLocationLatLng)
                    .title("My Location"));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(parkingLatLng)
                    .zoom(16.0f)
                    .bearing(0)
                    .tilt(30)
                    .build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }

}

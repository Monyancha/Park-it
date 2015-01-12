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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MainActivity extends Activity {

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

        setContentView(R.layout.activity_main);

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
        ParkingItem parkingItem = data.getParcelable("EXTRA_PARKING_ITEM");
        Double lat = parkingItem.getLat();
        Double lng = parkingItem.getLng();
        parkingLatLng = new LatLng(lat, lng);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        if (map!=null){
            map.addMarker(new MarkerOptions().position(parkingLatLng)
                    .title("Parking"));

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

    }

}

package masterung.androidthai.in.th.beerfriend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latADouble = 0, lngADouble = 0;
    private LocationManager locationManager;
    private Criteria criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Setup
        setup();

        if (latADouble == 0) {
            checkProvider();
        }

    }   // Main Method

    @Override
    protected void onResume() {
        super.onResume();
        checkProvider();


    }   // onResume

    private void checkProvider() {
        //        Network Type
        Location networkLocation = findLocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            latADouble = networkLocation.getLatitude();
            lngADouble = networkLocation.getLongitude();
        }


//        GPS Type
        Location gpsLocation = findLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latADouble = gpsLocation.getLatitude();
            lngADouble = gpsLocation.getLongitude();
        }

        Log.d("23AugV1", "lat ==> " + latADouble);
        Log.d("23AugV1", "lng ==> " + lngADouble);

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    public Location findLocation(String providerString) {

        Location location = null;
        if (locationManager.isProviderEnabled(providerString)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(providerString, 1000,
                    10, locationListener);
            location = locationManager.getLastKnownLocation(providerString);

        }


        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    private void setup() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        Setup Center Map
        if (latADouble == 0) {
            checkProvider();
        } else {
            LatLng latLng = new LatLng(latADouble, lngADouble);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            Log.d("23AugV1", "lat ==> " + latADouble);
            Log.d("23AugV1", "lng ==> " + lngADouble);

//            Create Marker
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_me));
            mMap.addMarker(markerOptions);

        }   // if


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng);
                mMap.addMarker(markerOptions);

                Toast.makeText(MapsActivity.this, "( lat=" + Double.toString(latLng.latitude) + " , Lng = " + Double.toString(latLng.longitude) + " )", Toast.LENGTH_SHORT).show();


            }
        });



    }   // onMapReady
}

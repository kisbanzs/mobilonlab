package hu.bme.aut.vizivandor.ui.turainditas;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.utravalo.Pozition;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class GoogleMapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private Button button;
    private ImageButton mylocationButton;
    private MyLocationActivity myLocation;
    private GoogleMap map;
    private GoogleMap mMap;
    private MapView googlemapview;
    private Pozition poz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.tura_inditas_googlemap);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map_id);
        mapFragment.getMapAsync(this);




               /* button = findViewById(R.id.startButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                boolean simulateRoute = true;
                System.out.println("Szimulacio inditasa");
                /*NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .directionsRoute(currentRoute)
                        .shouldSimulateRoute(simulateRoute)
                        .build();
// Call this method with Context from within an Activity
                NavigationLauncher.startNavigation(MainActivity.this, options);*/
           // }
        //});


        //googlemapview = findViewById(R.id.google_map_id);

       // mylocationButton = findViewById(R.id.mylocation);
       /* mylocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLocation.onMapReady(map);
            }
        });*/


    }


   /* public void onLocationResult(LocationResult locationResult) {
        for (Location location : locationResult.getLocations()) {
            if (currentBestLocation == null
                    || GeoUtils.isBetterLocation(location,
                    currentBestLocation)) {
                currentBestLocation = location;
            }
        }
    }*/


    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        /*map = googleMap;
        LatLng budapest = new LatLng(47.460886, 19.051869);
        map.addMarker(new MarkerOptions()
                .position(budapest)
                .title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(budapest));*/


        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setTrafficEnabled(true);

        LatLng hungary = new LatLng(47.460886, 19.051869);

        poz = new Pozition();
        addMarker(hungary, "Hungary", getString(R.string.desc_hungary));
        addMarker(poz.pozition("Tura1"), "Tiszabecs", "kezdo allomas");
        //drawPolygon();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hungary));
        //moveCamera(hungary);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(myLocation);
        mMap.setOnMyLocationClickListener(myLocation);
        
    }


    private void moveCamera(LatLng hungary) {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(hungary)
                .zoom(17)
                .bearing(90)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void drawPolygon() {
        PolygonOptions polyRect = new PolygonOptions().add(new LatLng(44, 19),
                new LatLng(44, 26),
                new LatLng(48, 26),
                new LatLng(48, 19));
        Polygon polygon = mMap.addPolygon(polyRect);
        polygon.setFillColor(Color.GREEN);
    }

    private void addMarker(LatLng hungary, String helysegnev, String tudnivalo) {
        Marker markerHU = mMap.addMarker(
                new MarkerOptions()
                        .position(hungary)
                        .title(helysegnev)
                        .snippet(tudnivalo)
                        .icon(BitmapDescriptorFactory.fromResource(
                                R.mipmap.ic_launcher)));
        markerHU.setDraggable(true);
    }



}


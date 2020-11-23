package hu.bme.aut.vizivandor.ui.turainditas;

import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import hu.bme.aut.vizivandor.R;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class GoogleMapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private Button button;
    private ImageButton mylocationButton;
    private MyLocationActivity myLocation;
    private GoogleMap map;
    private GoogleMap mMap;
    private MapView googlemapview;
    private Pozition poz;
    private LatLng latLng;
    private TextView megtett;
    private Location l;
    private LocationCallback mLocationCallback;
    private int i;
    private TimerTask timer;
    final private ArrayList<LocationHelper> currentlocations = new ArrayList<>();

    private DatabaseReference ref;
    private FirebaseUser mCurrentUser;




    private FusedLocationProviderClient fusedLocationClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_tura_inditas_googlemap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map_id);
        mapFragment.getMapAsync(this);

        ref = FirebaseDatabase.getInstance().getReference("VizivandorTerkep");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //megtett = findViewById(R.id.megtettut);
        i=1;

        ToggleButton toggle = (ToggleButton) findViewById(R.id.turainditasButton);


        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    navigationOn();
                } else {
                    navigationOff();
                }
            }
        });


    }

    private void navigationOn(){
        Toast.makeText(GoogleMapsActivity.this, "Megnyomtam a gombot", Toast.LENGTH_SHORT).show();

        ref.removeValue();
        i = 1;

        currentlocations.clear();

        //visszaadja a jelenlegi helyzet koordinátáit
        timer = new TimerTask() {
            @Override
            public void run() {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(GoogleMapsActivity.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(final Location location) {

                                if (location != null) {

                                    final DatabaseReference newPost = ref.child(String.valueOf(i));

                                    //System.out.println("itt jon a location " + location.getLatitude() + ", " + location.getLongitude());

                                    Double latitude = location.getLatitude();
                                    Double longitude = location.getLongitude();

                                    newPost.child("Latitude").setValue(latitude);
                                    newPost.child("Longitude").setValue(longitude);
                                    LocationHelper locationHelper = new LocationHelper(latitude, longitude);
                                    currentlocations.add(locationHelper);
                                    //cl.put(i, currentlocations);
                                    i++;

                                } else {
                                    System.out.println("Nem mukodik a helyzet felismero");
                                }
                            }
                        });
            }
        };

        new Timer().scheduleAtFixedRate(timer, 0, 1000);
    }

    private void navigationOff(){
        if(currentlocations!=null) {
            //System.out.println("Ez az " + currentlocations.get(1).getLatitude() + "helyzet. ");

            double vegeredmeny = 0;

            if(currentlocations.size() >= 2) {
                for (int i = 0; i < currentlocations.size() - 1; i++) {

                    vegeredmeny += utvonalszamitas(currentlocations.get(i).getLatitude(), currentlocations.get(i + 1).getLatitude(),
                            currentlocations.get(i).getLongitude(), currentlocations.get(i + 1).getLongitude());

                }
            }
            String v = String.format("%.2f", (vegeredmeny*100*1000));
            String vege = "Gratulálok, " + v + " métert tett meg a túrán!";

            Toast.makeText(this, vege, Toast.LENGTH_SHORT).show();
            //megtett.setText(vegeredmeny * 100 * 1000 + " m");

            System.out.println(vege);

            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("VizivandorToplista").push();

            ref2.child("username").setValue(mCurrentUser.getEmail());
            ref2.child("distance").setValue(v + " m");

        }


        //megallitom
        timer.cancel();
    }

    private double utvonalszamitas(double x1, double x2, double y1, double y2){
        System.out.println("x1: " + x1);
        System.out.println("x2: " + x2);
        System.out.println("y1: " + y1);
        System.out.println("y2: " + y2);
        return Math.sqrt( ( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) ) );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setTrafficEnabled(true);

        LatLng hungary = new LatLng(47.460886, 19.051869);

        poz = new Pozition();
        //addMarker(hungary, "Hungary", getString(R.string.desc_hungary));
        addMarker(poz.pozition("Tura1"), "Tiszabecs", "Kezdő állomás");
        addMarker(poz.pozition("Tura2"), "Tokaj", "Kezdő állomás");
        addMarker(poz.pozition("Tura3"), "Esztergom", "Kezdő állomás");
        addMarker(poz.pozition("Tura4"), "Dunasziget", "Kezdő állomás");
        addMarker(poz.pozition("Tura5"), "Paks", "Kezdő állomás");
        addMarker(poz.pozition("Tura6"), "Békés-Dánfok", "Kezdő állomás");
        addMarker(poz.pozition("Tura7"), "Tisza-tó", "Kezdő állomás");
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


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            String msg = "Updated location " + Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());
            //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            System.out.println("Itt vagyok bent az onlocationchanged függvényben");

            final LocationHelper helper = new LocationHelper(location.getLongitude(), location.getLatitude());

            FirebaseDatabase.getInstance().getReference("VizivandorTerkep")
                    .setValue(helper).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    final DatabaseReference newPost = FirebaseDatabase.getInstance().getReference("VizivandorTerkep").push();

                    newPost.child("Longitude").setValue(helper.getLongitude());
                    newPost.child("Latitude").setValue(helper.getLatitude());

                    System.out.println("Itt vagyok bent a függvényben");

                    if (task.isSuccessful()) {
                        Toast.makeText(GoogleMapsActivity.this, "Location Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GoogleMapsActivity.this, "Location Not Saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            latLng = new LatLng(location.getLatitude(), location.getLongitude());

            /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.google_map_id);
            mapFragment.getMapAsync(this);
*/
        }

    };


    @Override
    public void onLocationChanged(Location location) {

    }
}


package hu.bme.aut.vizivandor.ui.terkep;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.mylocation.DirectedLocationOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.util.ArrayList;

import hu.bme.aut.vizivandor.MainActivity;
import hu.bme.aut.vizivandor.R;

import static android.os.Build.VERSION_CODES.M;

public class MapFragment extends Fragment implements LocationListener {

    private static MapFragment INSTANCE = null;
    View view;

    MyLocationNewOverlay mLocationOverlay;
    MapView map = null;
    private GoogleMap gmap;
    private MapView osm;
    private MapController mc;
    private LocationManager locationManager;
    private CompassOverlay compassOverlay;
    private DirectedLocationOverlay locationOverlay;
    private static final int PERMISSAO_REQUERIDA = 1;

    private ArrayList<GeoPoint> kezdo_allomasok = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> felso_tisza = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> tokaj_bodrogzug = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> dunakanyar = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> szigetkoz = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> also_duna = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> korosok = new ArrayList<GeoPoint>();
    private ArrayList<GeoPoint> tisza_to = new ArrayList<GeoPoint>();

    private ArrayList<ArrayList<GeoPoint>> egesz_terkep = new ArrayList<ArrayList<GeoPoint>>();


    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new MapFragment();
        return INSTANCE;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.osmdroid_map, container, false);
        //return view;


        if (Build.VERSION.SDK_INT >= M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] permissoes = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissoes, PERMISSAO_REQUERIDA);
            }
        }



        //onde mostra a imagem do mapa
        final Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        osm = (MapView) view.findViewById(R.id.map_id);
        //MapView osm = view.findViewById(R.id.map_id);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mc = (MapController) osm.getController();
        mc.setZoom(15);
        //maximum mennyire lehet ránagyítani, és a legkisebb nézet pedig 1  világtérkép
        osm.setMaxZoomLevel(30.0);
        osm.setMinZoomLevel(2.3);
        //osm.setPadding(5,5,5,5);


        //7db túra kezdő állomásainak helye térképen jelölve kék kocsival

        kezdo_allomasok.add(new GeoPoint(48.104927,22.829234));
        kezdo_allomasok.add(new GeoPoint(48.137781,21.400946));
        kezdo_allomasok.add(new GeoPoint(47.789443,18.731162));
        kezdo_allomasok.add(new GeoPoint(47.940391,17.357821));
        kezdo_allomasok.add(new GeoPoint(46.592602,18.860733));
        kezdo_allomasok.add(new GeoPoint(46.759521,21.153568));
        kezdo_allomasok.add(new GeoPoint(47.645895,20.660313));

        felso_tisza.add(new GeoPoint(48.099701,22.624464));
        felso_tisza.add(new GeoPoint(48.061861,22.519040));
        felso_tisza.add(new GeoPoint(48.127612,22.340048));

        tokaj_bodrogzug.add(new GeoPoint(48.167749,21.398657));
        tokaj_bodrogzug.add(new GeoPoint(48.413187,21.637227));
        tokaj_bodrogzug.add(new GeoPoint(48.360649,21.693310));
        tokaj_bodrogzug.add(new GeoPoint(48.315088,21.568245));

        dunakanyar.add(new GeoPoint(47.765539,18.917588));
        dunakanyar.add(new GeoPoint(47.682486,19.085478));
        dunakanyar.add(new GeoPoint(47.571031,19.065174));

        szigetkoz.add(new GeoPoint(47.901520,17.425963));
        szigetkoz.add(new GeoPoint(47.835826,17.515642));

        also_duna.add(new GeoPoint(46.497939,18.916054));
        also_duna.add(new GeoPoint(46.199714,18.826217));
        also_duna.add(new GeoPoint(46.172750,18.940883));

        korosok.add(new GeoPoint(46.882307,21.031375));
        korosok.add(new GeoPoint(46.936571,20.836434));
        korosok.add(new GeoPoint(46.861703,20.539445));

        tisza_to.add(new GeoPoint(47.624602,20.745482));
        tisza_to.add(new GeoPoint(47.645770,20.660322));

        egesz_terkep.add(kezdo_allomasok);
        egesz_terkep.add(felso_tisza);
        egesz_terkep.add(tokaj_bodrogzug);
        egesz_terkep.add(dunakanyar);
        egesz_terkep.add(szigetkoz);
        egesz_terkep.add(also_duna);
        egesz_terkep.add(korosok);
        egesz_terkep.add(tisza_to);





        //ALSO MENÜ 3 GOMBJAINAK FELADATA:
        //1 INDULÓ ÁLLOMÁSOK GOMBJA
        ToggleButton toggle = (ToggleButton) view.findViewById(R.id.simpleSwitch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    for(GeoPoint g: kezdo_allomasok){
                        addMarker(g);
                    }

                    for(int i=1; i<egesz_terkep.size(); i++){
                        for(GeoPoint g: egesz_terkep.get(i)){
                            addPotty(g);
                        }
                    }


                    System.out.println("kocsi gomb ON");
                } else {
                    removeMarker();


                }
            }
        });



        //2 AKTUÁLIS POZÍCIÓ GOMBJA
        ImageButton btnCurrentLocation = view.findViewById(R.id.button_current_location);
        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //current location
                mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx),osm);
                mLocationOverlay.enableMyLocation();
                osm.getOverlays().add(mLocationOverlay);


                //GeoPoint current = new GeoPoint(mLocationOverlay.getMyLocation());
                //mc.animateTo(current);
                //mc.setZoom(20);
                //mapFragment.getMap().setMyLocationEnabled(true);
                System.out.println("jelenlegi helyzetre kattintas");

            }
        });

        //3 A 7 DB TÚRA HELYSZÍN KIVÁLASZTÓ GOMBJA

        String [] values = {"A Felső-Tisza","Tokaj és a Bodrogzug","Dunakanyar túra","Szigetköz szépségei","Alsó-Duna","Körösök","Tisza-tó"};
        Spinner spinner_turak = (Spinner) view.findViewById(R.id.spinner_turak);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_turak.setAdapter(adapter);
        System.out.println(String.valueOf(spinner_turak.getSelectedItem()));




        spinner_turak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Get Selected Class name from the list
                String selectedValues = parent.getItemAtPosition(position).toString();

                switch (selectedValues)
                {
                    case "A Felső-Tisza":
                        removeMarker();
                        for(GeoPoint g: felso_tisza){
                            addPotty(g);
                        }
                        break;

                    case "Tokaj és a Bodrogzug":
                        removeMarker();
                        for(GeoPoint g: tokaj_bodrogzug){
                            addPotty(g);
                        }
                        break;

                    case "Dunakanyar túra":
                        removeMarker();
                        for(GeoPoint g: dunakanyar){
                            addPotty(g);
                        }
                        break;

                    case "Szigetköz szépségei":
                        removeMarker();
                        for(GeoPoint g: szigetkoz){
                            addPotty(g);
                        }
                        break;

                    case "Alsó-Duna":
                        removeMarker();
                        for(GeoPoint g: also_duna){
                            addPotty(g);
                        }
                        break;

                    case "Körösök":
                        removeMarker();
                        for(GeoPoint g: korosok){
                            addPotty(g);
                        }
                        break;

                    case "Tisza-tó":
                        removeMarker();
                        for(GeoPoint g: tisza_to){
                            addPotty(g);
                        }
                        break;


                    default:
                        System.out.println("ez itt valami maaaaaas");
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });





        //Lagymanyosi obol geo pontja
        GeoPoint center = new GeoPoint(47.462740,19.057963);
        mc.animateTo(center);
        //addMarker(center);




        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);




        osm.setMapListener(new MapListener() {
            @Override
            public boolean onScroll(ScrollEvent event) {
                Log.i("Script", "onScroll()");
                return false;
            }

            @Override
            public boolean onZoom(ZoomEvent event) {
                Log.i("Script", "onZoom()");
                return false;
            }
        });


        return view;
    }

    public void addMarker (GeoPoint center){
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.ic_kocsimegallo));
        osm.getOverlays().add(marker);
        osm.invalidate();
        marker.setTitle("Induló állomás");
    }

    public void removeMarker(){

            osm.getOverlays().clear();
            osm.invalidate();
    }

    public void addPotty (GeoPoint center){
        Marker marker = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.ic_potty));
        //osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        osm.invalidate();
        marker.setTitle("Túra állomás");
    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_tura1:
                for(GeoPoint g: felso_tisza){
                    //mc.animateTo(g);
                    addPotty(g);
                }
                return true;
            case R.id.action_tura2:
                for(GeoPoint g: tokaj_bodrogzug){
                    //mc.animateTo(g);
                    addPotty(g);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSAO_REQUERIDA: {
                // Se a solicitação de permissão foi cancelada o array vem vazio.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissão cedida, recria a activity para carregar o mapa, só será executado uma vez
                    getActivity().recreate();

                }

            }
        }
    }

    public void onResume() {
        super.onResume();

    }


    public void onPause() {
        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint center = new GeoPoint(location.getLatitude(), location.getLongitude());

        mc.animateTo(center);
        addMarker(center);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //locationManager.removeUpdates(this);
        }

    }
}

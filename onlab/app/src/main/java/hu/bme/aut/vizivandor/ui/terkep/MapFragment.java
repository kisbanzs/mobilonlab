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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;

import hu.bme.aut.vizivandor.R;

import static android.os.Build.VERSION_CODES.M;

public class MapFragment extends Fragment implements LocationListener {

    private static MapFragment INSTANCE = null;
    View view;

    MapView map = null;
    private MapView osm;
    private MapController mc;
    private LocationManager locationManager;
    private CompassOverlay compassOverlay;
    private DirectedLocationOverlay locationOverlay;
    private static final int PERMISSAO_REQUERIDA = 1;


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
        Context ctx = getActivity().getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        osm = (MapView) view.findViewById(R.id.map_id);
        //MapView osm = view.findViewById(R.id.map_id);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mc = (MapController) osm.getController();
        mc.setZoom(15);

        //Lagymanyosi obol geo pontja
        GeoPoint center = new GeoPoint(47.462740,19.057963);
        mc.animateTo(center);
        //addMarker(center);


        GeoPoint kezdo_allomas_1 = new GeoPoint(48.104927,22.829234);
        GeoPoint kezdo_allomas_2 = new GeoPoint(48.137781,21.400946);
        GeoPoint kezdo_allomas_3 = new GeoPoint(47.789443,18.731162);
        GeoPoint kezdo_allomas_4 = new GeoPoint(47.940391,17.357821);
        GeoPoint kezdo_allomas_5 = new GeoPoint(46.592602,18.860733);
        GeoPoint kezdo_allomas_6 = new GeoPoint(46.759521,21.153568);
        GeoPoint kezdo_allomas_7 = new GeoPoint(47.645895,20.660313);

        ArrayList<GeoPoint> kezdo_allomasok = new ArrayList<GeoPoint>();
        kezdo_allomasok.add(kezdo_allomas_1);
        kezdo_allomasok.add(kezdo_allomas_2);
        kezdo_allomasok.add(kezdo_allomas_3);
        kezdo_allomasok.add(kezdo_allomas_4);
        kezdo_allomasok.add(kezdo_allomas_5);
        kezdo_allomasok.add(kezdo_allomas_6);
        kezdo_allomasok.add(kezdo_allomas_7);

        for(GeoPoint g: kezdo_allomasok){
            //mc.animateTo(g);
            addMarker(g);
        }


        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);


    /* grade no mapa
    LatLonGridlineOverlay2 overlay = new LatLonGridlineOverlay2();
    osm.getOverlays().add(overlay);
    */

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
        //osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        osm.invalidate();
        marker.setTitle("Kezdő állomás");
    }


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

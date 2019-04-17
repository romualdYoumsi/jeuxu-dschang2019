package com.example.jeuxu.Maps;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeuxu.Classe.Station;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Model.GetDirectionAT;
import com.example.jeuxu.Model.GetStationAT;
import com.example.jeuxu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import static com.example.jeuxu.Common.Common.LOCATION_REQ_CODE;

public class MapsLocation extends FragmentActivity implements OnMapReadyCallback, GetStationAT.GetStationATResult,
        ClusterManager.OnClusterItemClickListener<Station>, GoogleMap.InfoWindowAdapter, ClusterManager.OnClusterItemInfoWindowClickListener<Station>, GetDirectionAT.GetDirectionATResult {

    private GoogleMap mMap;
    private ArrayList<Station> stations;
    private ClusterManager<Station> mClusterManager;
    private Station stationClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, LOCATION_REQ_CODE);
        }


        stations = new ArrayList<>();
        stationClick = null;
        new GetStationAT(this).execute();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        }
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

        mClusterManager = new ClusterManager<>(this, mMap);

        mClusterManager.setOnClusterItemClickListener(this);

        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);


        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        mMap.setOnInfoWindowClickListener(mClusterManager);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }

        rafraichirCarte();

    }

    @Override
    public void onClusterItemInfoWindowClick(Station station) {
        Toast.makeText(this, station.getName(), Toast.LENGTH_SHORT).show();
       // new GetDirectionAT(this).execute();
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (stationClick == null) {
            return null;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.marker_layout, null);
        TextView txt_map = (TextView) view.findViewById(R.id.txt_map);
        TextView txt_address = (TextView) view.findViewById(R.id.txt_address);


        txt_map.setText(stationClick.getName());
        txt_address.setText(stationClick.getAddress());
        return view;
    }




    @Override
    public boolean onClusterItemClick(Station station) {

        stationClick = station;
        return false;
    }





    @Override
    public void stationCharger(ArrayList<Station> stations) {
        this.stations.clear();
        this.stations.addAll(stations);
        rafraichirCarte();
    }

    @Override
    public void getStationATResultErreur(Exception exception) {
        exception.printStackTrace();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void rafraichirCarte() {
        if (mMap == null) {
            return;
        }
        mMap.clear();
        if (!stations.isEmpty()) {

            mClusterManager.clearItems();
            mClusterManager.addItems(stations);

            LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();

            for (Station station : stations) {

                latLngBounds.include(station.getPosition());

            }
            int padding = 200;
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), padding));
        }
    }


    @Override
    public void directionCharger() {

    }

    @Override
    public void getdirectionATResultErreur(Exception exception) {
        exception.printStackTrace();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

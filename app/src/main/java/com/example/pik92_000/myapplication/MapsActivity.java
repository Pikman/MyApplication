package com.example.pik92_000.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapLongClickListener,
        OnMapClickListener,OnMarkerDragListener, OnMapReadyCallback {

    static double MarkLat = 0, MarkLong = 0;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();

        //MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);

        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapClickListener(this);

        Toast.makeText(getApplicationContext(), "Точка установлена в "+ MarkLat + MarkLong, Toast.LENGTH_LONG).show();

        onMapReady(mMap);
    }



    @Override
    public void onMapReady(GoogleMap map) {
        LatLng moscow = new LatLng(55.7558, 37.6173);

        mMap.setMyLocationEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow, 13));

        mMap.addMarker(new MarkerOptions()
                .title("Marker")
                .snippet("The most populous city in Australia.")
                .position(new LatLng(MarkLat, MarkLong))
                .draggable(true));

    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
    }

        @Override
    public void onMarkerDragStart(Marker arg0) {
        // TODO Auto-generated method stub

    }

    public void onMarkerDragEnd(Marker arg0) {
        // TODO Auto-generated method stub
        LatLng dragPosition = arg0.getPosition();
        MarkLat = dragPosition.latitude;
        MarkLong = dragPosition.longitude;
        Log.i("info", "on drag end :" + MarkLat + " MarkLong :" + MarkLong);
        Toast.makeText(getApplicationContext(), "Точка установлена в "+ MarkLat + MarkLong, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMarkerDrag(Marker arg0){

    }

    @Override
    public void onMapLongClick(LatLng arg0) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "asd" + MarkLong, Toast.LENGTH_LONG).show();
    }
}

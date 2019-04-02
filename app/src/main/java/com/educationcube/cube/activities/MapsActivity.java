package com.educationcube.cube.activities;


import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.educationcube.cube.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if(mapFragment!=null){
            Log.e("mapready","notnull:"+mapFragment);
            mapFragment.getMapAsync(MapsActivity.this);
        }else{
            Log.e("mapready","map is null");
        }
        Log.e("mapready","creategetMapAsync");
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
        mUiSettings = mMap.getUiSettings();
        googleMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng pkcLoc = new LatLng(43.768241,  -79.228809);
        mMap.addMarker(new MarkerOptions().position(pkcLoc).title("Education Cube").snippet("Markham Rd, Scarborough, ON M1H 2Y2"));
        mUiSettings.setZoomControlsEnabled(true);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(pkcLoc , 15.0f) );

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        final AlertDialog alertDialog =new AlertDialog.Builder(MapsActivity.this).create();
        alertDialog.setTitle("Education Cube");
        alertDialog.setMessage(" info@education-cube.com " +
                "\n647 247 1776 ");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
              alertDialog.dismiss();
          }
      });
        alertDialog.show();
        return false;
    }
}

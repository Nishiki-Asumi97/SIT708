package com.example.lostandfound;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        db = new DatabaseHelper(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Cursor cursor = db.getAllItemsCursor(); // Add this method in DB
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String locStr = cursor.getString(cursor.getColumnIndex("location"));
                Geocoder geocoder = new Geocoder(this);
                try {
                    List<Address> addrList = geocoder.getFromLocationName(locStr, 1);
                    if (!addrList.isEmpty()) {
                        LatLng pos = new LatLng(addrList.get(0).getLatitude(), addrList.get(0).getLongitude());
                        mMap.addMarker(new MarkerOptions().position(pos).title(cursor.getString(1) + ": " + cursor.getString(4)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 10));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
    }
}

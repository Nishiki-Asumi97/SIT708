package com.example.lostandfound;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class AddAdvertActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private EditText loc;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_advert);

        db = new DatabaseHelper(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize Places API
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCi7e7sfDNMSjczkeAUur9h2hAblyUBKjA");
        }

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText name = findViewById(R.id.etName);
        EditText phone = findViewById(R.id.etPhone);
        EditText desc = findViewById(R.id.etDescription);
        EditText date = findViewById(R.id.etDate);
        loc = findViewById(R.id.etLocation);
        loc.setFocusable(false);

        loc.setOnClickListener(v -> {
            List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS);
            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).build(AddAdvertActivity.this);
            startActivityForResult(intent, 100);
        });

        Button save = findViewById(R.id.btnSave);

        save.setOnClickListener(v -> {
            String type = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
            db.insertItem(type, name.getText().toString(), phone.getText().toString(),
                    desc.getText().toString(), date.getText().toString(), loc.getText().toString());
            Toast.makeText(this, "Advert saved!", Toast.LENGTH_SHORT).show();
            finish();
        });

        Button getLocationBtn = findViewById(R.id.btnGetLocation);
        getLocationBtn.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 101);
                return;
            }

            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        if (!addresses.isEmpty()) {
                            loc.setText(addresses.get(0).getAddressLine(0));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            loc.setText(place.getAddress());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

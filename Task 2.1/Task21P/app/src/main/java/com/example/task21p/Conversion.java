package com.example.task21p;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class Conversion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinnerSource = findViewById(R.id.spinner);
        Spinner spinnerDest = findViewById(R.id.spinner2);
        EditText input = findViewById(R.id.editTextNumber4);
        Button convertBtn = findViewById(R.id.button2);
        TextView result = findViewById(R.id.textView6);

        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        // Set Listener to load units based on selected category
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedCategory = position;

                ArrayAdapter<CharSequence> unitAdapter;

                switch (selectedCategory) {
                    case 0: // Length
                        unitAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.length_units, android.R.layout.simple_spinner_item);
                        break;
                    case 1: // Weight
                        unitAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.weight_units, android.R.layout.simple_spinner_item);
                        break;
                    case 2: // Temperature
                        unitAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                R.array.temperature_units, android.R.layout.simple_spinner_item);
                        break;
                    default:
                        unitAdapter = null;
                }

                if (unitAdapter != null) {
                    unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSource.setAdapter(unitAdapter);
                    spinnerDest.setAdapter(unitAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        convertBtn.setOnClickListener(v -> {
            String sourceUnit = spinnerSource.getSelectedItem().toString();
            String destUnit = spinnerDest.getSelectedItem().toString();
            String inputVal = input.getText().toString();

            if (!inputVal.isEmpty()) {
                double value = Double.parseDouble(inputVal);
                double converted = convertUnits(sourceUnit, destUnit, value);
                result.setText(String.valueOf(converted));
            } else {
                result.setText("Input cannot be empty!");
            }


            // Check for empty input
            if (inputVal.isEmpty()) {
                Toast.makeText(Conversion.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                return;
            }

            double inputValue;

            try {
                inputValue = Double.parseDouble(inputVal);
            } catch (NumberFormatException e) {
                Toast.makeText(Conversion.this, "Invalid number entered", Toast.LENGTH_SHORT).show();
                return;
            }

            // If both units are the same
            if (sourceUnit.equals(destUnit)) {
                Toast.makeText(Conversion.this, "Both source unit and the destination unit are the same!", Toast.LENGTH_SHORT).show();
                //result.setText("Converted Value: " + inputValue);
                return;
            }

            try {
                double resultConversion = convertUnits(sourceUnit, destUnit, inputValue);
                result.setText(String.format("%.2f", resultConversion));

            } catch (IllegalArgumentException e) {
                Toast.makeText(Conversion.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    //unit conversion methods
    private double convertUnits(String source, String dest, double value) {

        // Length in cm
        HashMap<String, Double> lengthMap = new HashMap<>();
        lengthMap.put("Inch", 2.54);
        lengthMap.put("Foot", 30.48);
        lengthMap.put("Yard", 91.44);
        lengthMap.put("Mile", 160934.0);
        lengthMap.put("Centimeter", 1.0);
        lengthMap.put("Kilometer", 100000.0);

        // Weight in kg
        HashMap<String, Double> weightMap = new HashMap<>();
        weightMap.put("Pound", 0.453592);
        weightMap.put("Ounce", 0.0283495);
        weightMap.put("Ton", 907.185);
        weightMap.put("Gram", 0.001);
        weightMap.put("Kilogram", 1.0);

        if (lengthMap.containsKey(source) && lengthMap.containsKey(dest)) {
            double sourceToCm = lengthMap.get(source);
            double destToCm = lengthMap.get(dest);
            return (value * sourceToCm) / destToCm;
        } else if (weightMap.containsKey(source) && weightMap.containsKey(dest)) {
            double sourceToKg = weightMap.get(source);
            double destToKg = weightMap.get(dest);
            return (value * sourceToKg) / destToKg;
        } else if (source.equals("Celsius") && dest.equals("Fahrenheit")) {
            return (value * 1.8) + 32;
        } else if (source.equals("Fahrenheit") && dest.equals("Celsius")) {
            return (value - 32) / 1.8;
        } else if (source.equals("Celsius") && dest.equals("Kelvin")) {
            return value + 273.15;
        } else if (source.equals("Kelvin") && dest.equals("Celsius")) {
            return value - 273.15;
        } else {
            throw new IllegalArgumentException("Invalid conversion between " + source + " and " + dest);
        }
    }
}
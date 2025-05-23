package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnShowAll = findViewById(R.id.btnShowAll);
        Button btnMap = findViewById(R.id.btnShowMap);
        btnMap.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class)));

        btnCreate.setOnClickListener(v -> startActivity(new Intent(this, AddAdvertActivity.class)));
        btnShowAll.setOnClickListener(v -> startActivity(new Intent(this, ListItemsActivity.class)));
    }
}

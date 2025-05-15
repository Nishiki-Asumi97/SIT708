package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListItemsActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_list_items);

        db = new DatabaseHelper(this);
        ListView listView = findViewById(R.id.listView);

        List<String> items = db.getAllItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = items.get(position);
            int itemId = Integer.parseInt(selected.split(":")[0].trim());
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("item_id", itemId);
            startActivity(intent);
        });
    }
}

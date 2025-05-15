package com.example.lostandfound;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    DatabaseHelper db;
    int itemId;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_detail);

        db = new DatabaseHelper(this);
        TextView detail = findViewById(R.id.tvDetail);
        Button remove = findViewById(R.id.btnRemove);

        itemId = getIntent().getIntExtra("item_id", -1);
        Cursor c = db.getItemById(itemId);

        if (c.moveToFirst()) {
            String info = c.getString(1) + " " + c.getString(4) + "\n" +
                    c.getString(5) + " ago\nAt " + c.getString(6);
            detail.setText(info);
        }

        remove.setOnClickListener(v -> {
            db.deleteItem(itemId);
            Toast.makeText(this, "Item removed!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

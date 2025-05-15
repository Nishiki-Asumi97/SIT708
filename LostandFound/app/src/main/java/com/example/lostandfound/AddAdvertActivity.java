package com.example.lostandfound;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddAdvertActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_advert);

        db = new DatabaseHelper(this);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        EditText name = findViewById(R.id.etName);
        EditText phone = findViewById(R.id.etPhone);
        EditText desc = findViewById(R.id.etDescription);
        EditText date = findViewById(R.id.etDate);
        EditText loc = findViewById(R.id.etLocation);
        Button save = findViewById(R.id.btnSave);

        save.setOnClickListener(v -> {
            String type = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
            db.insertItem(type, name.getText().toString(), phone.getText().toString(),
                    desc.getText().toString(), date.getText().toString(), loc.getText().toString());
            Toast.makeText(this, "Advert saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

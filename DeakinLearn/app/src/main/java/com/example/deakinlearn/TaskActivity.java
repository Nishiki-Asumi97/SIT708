package com.example.deakinlearn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TaskActivity extends AppCompatActivity {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(v -> {
            // On submit, proceed to results screen
            startActivity(new Intent(TaskActivity.this, ResultsActivity.class));
        });
    }
}
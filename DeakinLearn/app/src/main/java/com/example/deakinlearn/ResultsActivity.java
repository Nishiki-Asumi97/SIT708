package com.example.deakinlearn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultsActivity extends AppCompatActivity {

    TextView scoreText;
    Button homeButton, retakeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        scoreText = findViewById(R.id.textScore);
        homeButton = findViewById(R.id.buttonHome);
        retakeButton = findViewById(R.id.buttonRetake);

        // For now, set dummy score
        scoreText.setText("You scored 2/2");

        homeButton.setOnClickListener(v -> {
            startActivity(new Intent(ResultsActivity.this, HomeActivity.class));
        });

        retakeButton.setOnClickListener(v -> {
            startActivity(new Intent(ResultsActivity.this, TaskActivity.class));
        });
    }
}
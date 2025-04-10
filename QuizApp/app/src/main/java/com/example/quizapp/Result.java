package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        // Retrieve the passed data from the Intent
        int userScore = getIntent().getIntExtra("USER_SCORE", 0); // Default to 0 if no score passed

        // Find the TextViews in the layout
        TextView textView4 = findViewById(R.id.textView4);

        textView4.setText(userScore + "/3");

        // Set up the "Take a new quiz" button
        Button newQuizButton = findViewById(R.id.button2);
        newQuizButton.setOnClickListener(v -> {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
        });

        // Set up the "Finish" button
        Button finishButton = findViewById(R.id.button5);
        finishButton.setOnClickListener(v -> {
            // Finish the app or go back to the main menu
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
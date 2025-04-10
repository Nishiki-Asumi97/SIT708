package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Question3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question3);

        int correctAnswerIndex = 0; //

        Button option1 = findViewById(R.id.buttonh2o);  // Berlin
        Button option2 = findViewById(R.id.buttonco2);  // Paris
        Button option3 = findViewById(R.id.buttono2);  // Madrid
        Button submitButton = findViewById(R.id.submit3);

        final int[] selectedOption = {-1};  // store selected option (0, 1, 2)

        option1.setOnClickListener(v -> {
            selectedOption[0] = 0;
            resetButtonColors(option1, option2, option3);
            option1.setBackgroundColor(Color.LTGRAY);
        });

        option2.setOnClickListener(v -> {
            selectedOption[0] = 1;
            resetButtonColors(option1, option2, option3);
            option2.setBackgroundColor(Color.LTGRAY);
        });

        option3.setOnClickListener(v -> {
            selectedOption[0] = 2;
            resetButtonColors(option1, option2, option3);
            option3.setBackgroundColor(Color.LTGRAY);
        });

        submitButton.setOnClickListener(v -> {
            if (selectedOption[0] == -1) {
                Toast.makeText(Question3.this, "Answer the question 1 before moving to the next!", Toast.LENGTH_SHORT).show();
                return;  // Stop if nothing selected
            }

            // Check correct / wrong
            if (selectedOption[0] == correctAnswerIndex) {
                // Correct → Green
                getSelectedButton(selectedOption[0], option1, option2, option3).setBackgroundColor(Color.GREEN);
                MainActivity.correctAnswersCount++;
            } else {
                // Wrong → Red
                getSelectedButton(selectedOption[0], option1, option2, option3).setBackgroundColor(Color.RED);

                // Highlight correct answer → Green
                getSelectedButton(correctAnswerIndex, option1, option2, option3).setBackgroundColor(Color.GREEN);
            }

            // Delay for 1 second before going to next question
            new Handler(Looper.getMainLooper()).postDelayed(() -> {

                Intent intent = new Intent(Question3.this, Result.class);

                int score = MainActivity.correctAnswersCount; // This should be the final score after the quiz is complete
                // Passing the name and score using intent extras
                intent.putExtra("USER_SCORE", score);

                startActivity(intent);
                finish();
            }, 1000);
        });

        //changing the progress bar status
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(3); // This is Question 3


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //changing the button colors back to white
    private void resetButtonColors(Button... buttons) {
        for (Button btn : buttons) {
            btn.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        }
    }

    private Button getSelectedButton(int index, Button option1, Button option2, Button option3) {
        switch (index) {
            case 0: return option1;
            case 1: return option2;
            case 2: return option3;
        }
        return null;
    }
}
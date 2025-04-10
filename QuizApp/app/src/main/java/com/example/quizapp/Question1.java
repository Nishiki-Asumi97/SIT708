package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;
import android.os.Looper;


public class Question1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question1);

        TextView welcomeText = findViewById(R.id.textView3);
        String userName = getIntent().getStringExtra("USER_NAME"); // receiving the first name from the main activity

        welcomeText.setText("Welcome " + userName + "!");

        int correctAnswerIndex = 1; //

        Button option1 = findViewById(R.id.button2);  // Berlin
        Button option2 = findViewById(R.id.button3);  // Paris
        Button option3 = findViewById(R.id.button4);  // Madrid
        Button submitButton = findViewById(R.id.button5);

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
                Toast.makeText(Question1.this, "Answer the question 1 before moving to the next!", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Question1.this, Question2.class);
                startActivity(intent);
                finish();
            }, 1000);
        });

        //changing the progress bar status
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(1); // This is Question 1


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
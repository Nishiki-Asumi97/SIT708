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

import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Question2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question2);

        int correctAnswerIndex = 0; //

        Button option1 = findViewById(R.id.buttonMars);  // Mars
        Button option2 = findViewById(R.id.buttonJup);  // Jupitor
        Button option3 = findViewById(R.id.buttonVenus);  // Venus
        Button submitButton = findViewById(R.id.submit2);

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
                Toast.makeText(Question2.this, "Answer the question 2 before moving to the next!", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Question2.this, Question3.class);
                startActivity(intent);
                finish();
            }, 1000);
        });


        //chaging the progress bar status
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(2); // This is Question 2

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

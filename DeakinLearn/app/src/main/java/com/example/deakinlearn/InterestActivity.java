package com.example.deakinlearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class InterestActivity extends AppCompatActivity {

    private final String[] topics = {
            "Algorithms", "Data Structures", "Web Development", "Testing",
            "AI", "Mobile Development", "Data Science", "Cybersecurity",
            "Game Dev", "Networking", "UI/UX", "Databases"
    };

    private final List<String> selectedTopics = new ArrayList<>();
    private static final int MAX_SELECTION = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        Button nextButton = findViewById(R.id.buttonNext);

        for (String topic : topics) {
            ToggleButton toggleButton = new ToggleButton(this);
            toggleButton.setTextOff(topic);
            toggleButton.setTextOn(topic);
            toggleButton.setText(topic);
            toggleButton.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (selectedTopics.size() < MAX_SELECTION) {
                        selectedTopics.add(topic);
                    } else {
                        toggleButton.setChecked(false);
                        Toast.makeText(this, "Max 10 topics allowed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    selectedTopics.remove(topic);
                }
            });

            gridLayout.addView(toggleButton);
        }

        nextButton.setOnClickListener(v -> {
            // Save selectedTopics or pass to next screen
            startActivity(new Intent(InterestActivity.this, HomeActivity.class));
        });
    }
}
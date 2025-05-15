package com.example.deakinlearn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerTasks = findViewById(R.id.recyclerTasks);
        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));

        // Dummy list of tasks
        List<String> dummyTasks = Arrays.asList(
                "🧠 Task 1: Data Structures",
                "🤖 Task 2: AI Fundamentals",
                "💻 Task 3: Web Development"
        );

        TaskAdapter adapter = new TaskAdapter(dummyTasks, this);
        recyclerTasks.setAdapter(adapter);
    }
}

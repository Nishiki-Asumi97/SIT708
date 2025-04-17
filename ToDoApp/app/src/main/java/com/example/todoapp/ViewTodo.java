package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.DAO.TaskAdapter;
import com.example.todoapp.DAO.TaskDao;
import com.example.todoapp.DB.TaskDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewTodo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_todo);

        // Setup DB
        TaskDb db = TaskDb.getInstance(this);
        taskDao = db.taskDao();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycler_view_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskDao.getAllTasks());
        recyclerView.setAdapter(taskAdapter);

        // Item click to edit
        taskAdapter.setOnItemClickListener(task -> {
            Intent intent = new Intent(ViewTodo.this, AddEditTodo.class);
            intent.putExtra("task_id", task.id);
            intent.putExtra("title", task.title);
            intent.putExtra("description", task.description);
            intent.putExtra("dueDate", task.dueDate);
            startActivity(intent);
        });

        // Long click to delete
        taskAdapter.setOnItemLongClickListener(task -> {
            taskDao.delete(task);
            refreshList();
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
        });

        // FAB to add new task
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ViewTodo.this, AddEditTodo.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        taskAdapter.updateTasks(taskDao.getAllTasks());
    }
}
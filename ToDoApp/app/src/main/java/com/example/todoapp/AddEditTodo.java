package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.DAO.TaskDao;
import com.example.todoapp.DB.TaskDb;
import com.example.todoapp.Model.Task;

public class AddEditTodo extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText, dueDateEditText;
    private TaskDao taskDao;
    private int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_todo);

        titleEditText = findViewById(R.id.edit_text_title);
        descriptionEditText = findViewById(R.id.edit_text_description);
        dueDateEditText = findViewById(R.id.edit_text_due_date);
        Button saveButton = findViewById(R.id.button_save);

        taskDao = TaskDb.getInstance(this).taskDao();

        // Check if we are editing
        Intent intent = getIntent();
        if (intent.hasExtra("task_id")) {
            taskId = intent.getIntExtra("task_id", -1);
            titleEditText.setText(intent.getStringExtra("title"));
            descriptionEditText.setText(intent.getStringExtra("description"));
            dueDateEditText.setText(intent.getStringExtra("dueDate"));
        }

        // Save button click
        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String desc = descriptionEditText.getText().toString().trim();
            String date = dueDateEditText.getText().toString().trim();

            // Validations
            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Title and Due Date are required", Toast.LENGTH_SHORT).show();
                return;
            }

            Task task = new Task();
            task.title = title;
            task.description = desc;
            task.dueDate = date;

            if (taskId == -1) {
                // Pop up after adding a new task
                taskDao.insert(task);
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
            } else {
                // Pop up after updating a Task
                task.id = taskId;
                taskDao.update(task);
                Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
            }

            finish(); // Returning to View screen
        });
    }
}

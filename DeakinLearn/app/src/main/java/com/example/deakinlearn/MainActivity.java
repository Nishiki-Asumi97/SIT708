package com.example.deakinlearn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);
        signup = findViewById(R.id.textSignup);

        login.setOnClickListener(v -> {
            // Dummy login logic
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });
    }
}
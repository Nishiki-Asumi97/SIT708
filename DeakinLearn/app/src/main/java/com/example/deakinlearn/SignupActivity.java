package com.example.deakinlearn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    EditText username, email, confirmEmail, password, confirmPassword, phone;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.editUsername);
        email = findViewById(R.id.editEmail);
        confirmEmail = findViewById(R.id.editConfirmEmail);
        password = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.editConfirmPassword);
        phone = findViewById(R.id.editPhone);
        createAccount = findViewById(R.id.buttonCreateAccount);

        createAccount.setOnClickListener(v -> {
            // Dummy account creation and navigation to interest selection
            startActivity(new Intent(SignupActivity.this, InterestActivity.class));
        });
    }
}
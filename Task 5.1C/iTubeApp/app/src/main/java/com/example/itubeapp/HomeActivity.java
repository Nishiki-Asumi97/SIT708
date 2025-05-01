package com.example.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.itubeapp.Database.DBHelper;

public class HomeActivity extends AppCompatActivity {

    EditText etUrl;
    Button btnPlay, btnAddToPlaylist, btnMyPlaylist;
    DBHelper dbHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);
        username = getIntent().getStringExtra("username");

        etUrl = findViewById(R.id.etUrl);
        btnPlay = findViewById(R.id.btnPlay);
        btnAddToPlaylist = findViewById(R.id.btnAddToPlaylist);
        btnMyPlaylist = findViewById(R.id.btnMyPlaylist);

        btnPlay.setOnClickListener(v -> {
            String url = etUrl.getText().toString();
            if (!url.isEmpty()) {
                Intent i = new Intent(this, VideoPlayerActivity.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });

        btnAddToPlaylist.setOnClickListener(v -> {
            String url = etUrl.getText().toString();
            if (!url.isEmpty()) {
                dbHelper.addVideoToPlaylist(username, url);
                Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show();
            }
        });

        btnMyPlaylist.setOnClickListener(v -> {
            Intent i = new Intent(this, PlaylistActivity.class);
            i.putExtra("username", username);
            startActivity(i);
        });
    }
}

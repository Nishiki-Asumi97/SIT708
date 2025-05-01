package com.example.itubeapp;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.itubeapp.Database.DBHelper;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    LinearLayout playlistContainer;
    DBHelper dbHelper;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistContainer = findViewById(R.id.playlistContainer);
        dbHelper = new DBHelper(this);
        username = getIntent().getStringExtra("username");

        List<String> playlist = dbHelper.getPlaylist(username);

        for (String videoUrl : playlist) {
            TextView tv = new TextView(this);
            tv.setText(videoUrl);
            tv.setTextSize(16);
            tv.setPadding(0, 12, 0, 12);
            playlistContainer.addView(tv);
        }
    }
}

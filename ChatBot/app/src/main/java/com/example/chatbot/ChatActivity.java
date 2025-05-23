package com.example.chatbot;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatLayout;
    private EditText messageInput;
    private OkHttpClient client = new OkHttpClient();

    private void addMessage(String message, boolean isUser) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setPadding(20, 12, 20, 12);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        textView.setBackgroundResource(isUser ? android.R.drawable.dialog_holo_light_frame : android.R.drawable.dialog_holo_dark_frame);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 8, 8, 8);
        params.gravity = isUser ? android.view.Gravity.END : android.view.Gravity.START;
        textView.setLayoutParams(params);

        chatLayout.addView(textView);
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void getBotReply(String userMessage) {
        String url = "http://10.0.2.2:5000/chat";
        JSONObject json = new JSONObject();
        try {
            json.put("message", userMessage);
        } catch (Exception ignored) {}

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder().url(url).post(body).build();

        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String reply = new JSONObject(response.body().string()).getString("reply");
                    runOnUiThread(() -> addMessage(reply, false));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> addMessage("Error: Could not reach bot", false));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatLayout = findViewById(R.id.chatLayout);
        messageInput = findViewById(R.id.messageInput);
        ImageButton sendButton = findViewById(R.id.sendButton);

        String username = getIntent().getStringExtra("USERNAME");
        addMessage("Welcome " + username + "!", false);

        sendButton.setOnClickListener(v -> {
            String msg = messageInput.getText().toString().trim();
            if (!msg.isEmpty()) {
                addMessage(msg, true);
                messageInput.setText("");
                getBotReply(msg);
            }
        });
    }
}
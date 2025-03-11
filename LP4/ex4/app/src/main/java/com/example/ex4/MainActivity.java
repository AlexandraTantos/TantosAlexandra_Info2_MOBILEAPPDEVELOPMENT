package com.example.ex4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText messageInput;
    TextView chatView;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput = findViewById(R.id.messageInput);
        chatView = findViewById(R.id.chatView);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString();
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            intent.putExtra("msgFromA", message);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String reply = intent.getStringExtra("msgFromB");
        if (reply != null) {
            chatView.append("\nUser B: " + reply);
        }
    }
}

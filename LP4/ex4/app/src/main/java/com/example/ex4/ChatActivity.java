package com.example.ex4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    TextView receivedText;
    EditText replyInput;
    Button replyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        receivedText = findViewById(R.id.receivedText);
        replyInput = findViewById(R.id.replyInput);
        replyButton = findViewById(R.id.replyButton);

        String receivedMsg = getIntent().getStringExtra("msgFromA");
        receivedText.setText("User A: " + receivedMsg);

        replyButton.setOnClickListener(v -> {
            String reply = replyInput.getText().toString();
            Intent intent = new Intent(ChatActivity.this, MainActivity.class);
            intent.putExtra("msgFromB", reply);
            startActivity(intent);
        });
    }
}

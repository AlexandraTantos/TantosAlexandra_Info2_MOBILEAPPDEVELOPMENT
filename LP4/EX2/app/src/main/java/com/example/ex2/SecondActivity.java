package com.example.ex2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);
        Button btnNext = findViewById(R.id.btn_next);
        Button btnBack = findViewById(R.id.btn_back);

        imageView.setImageResource(R.drawable.image2);
        textView.setText("This is the second activity!");

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, com.example.ex2.ThirdActivity.class);
            startActivity(intent);
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

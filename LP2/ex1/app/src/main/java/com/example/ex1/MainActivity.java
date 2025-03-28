package com.example.ex1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Hello, Toast!", Toast.LENGTH_SHORT).show();
        Button buttonToast = findViewById(R.id.buttonToast);
        Button buttonCount = findViewById(R.id.buttonCount);
        TextView textViewCounter = findViewById(R.id.textViewCounter);
        buttonToast.setOnClickListener(view ->{
            Toast.makeText(MainActivity.this, "You pressed toast button", Toast.LENGTH_SHORT).show();
        });
        buttonCount.setOnClickListener(view -> {
            count++;
            textViewCounter.setText(String.valueOf(count));
        });
    }

}
package com.example.ex1;

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

    private Button btnAdd, btnSub, btnMul, btnDiv;
    private EditText firstNr, secondNr;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAdd = findViewById(R.id.addButton);
        btnSub = findViewById(R.id.subButton);
        btnMul = findViewById(R.id.mulButton);
        btnDiv = findViewById(R.id.divButton);
        firstNr = findViewById(R.id.firstNumber);
        secondNr = findViewById(R.id.secondNumber);
        result = findViewById(R.id.outputText);

        btnAdd.setOnClickListener(v -> add());
        btnSub.setOnClickListener(v -> sub());
        btnMul.setOnClickListener(v -> mul());
        btnDiv.setOnClickListener(v -> div());
    }

    private void add() {
        String num1Str = firstNr.getText().toString();
        String num2Str = secondNr.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double nr1 = Integer.parseInt(num1Str);
            double nr2 = Integer.parseInt(num2Str);
            double sum = nr1 + nr2;
            result.setText("Result: " + sum);
        } else {
            result.setText("Please enter both numbers!");
        }
    }
    private void sub(){
        String num1Str = firstNr.getText().toString();
        String num2Str = secondNr.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double nr1 = Integer.parseInt(num1Str);
            double nr2 = Integer.parseInt(num2Str);
            double substraction = nr1 - nr2;
            result.setText("Result: " + substraction);
        } else {
            result.setText("Please enter both numbers!");
        }
    }
    private void div(){
        String num1Str = firstNr.getText().toString();
        String num2Str = secondNr.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double nr1 = Integer.parseInt(num1Str);
            double nr2 = Integer.parseInt(num2Str);
            double division = nr1 / nr2;
            result.setText("Result: " + division);
        } else {
            result.setText("Please enter both numbers!");
        }
    }
    private void mul(){
        String num1Str = firstNr.getText().toString();
        String num2Str = secondNr.getText().toString();

        if (!num1Str.isEmpty() && !num2Str.isEmpty()) {
            double nr1 = Integer.parseInt(num1Str);
            double nr2 = Integer.parseInt(num2Str);
            double multiplication = nr1 * nr2;
            result.setText("Result: " + multiplication);
        } else {
            result.setText("Please enter both numbers!");
        }
    }
}

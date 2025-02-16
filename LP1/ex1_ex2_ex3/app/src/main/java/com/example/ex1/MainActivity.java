package com.example.ex1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputNumber1, inputNumber2, inputNumber3, editText;
    private TextView resultText, textView, convertedText;
    private Button checkButton, buttonSort, convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.inputText);
        textView = findViewById(R.id.outputText);
        buttonSort = findViewById(R.id.sortButton);

        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                String sortedString = customStringSorting(input);
                textView.setText(sortedString);
            }
        });

        inputNumber1 = findViewById(R.id.inputTextNumber1);
        inputNumber2 = findViewById(R.id.inputTextNumber2);
        resultText = findViewById(R.id.outputText2);
        checkButton = findViewById(R.id.checkButton);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFriendlyNumbers();
            }
        });

        inputNumber3 = findViewById(R.id.inputTextNumber3);
        convertedText = findViewById(R.id.outputText3);
        convertButton = findViewById(R.id.hexButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToHexadecimal();
            }
        });
    }

    public static String customStringSorting(String input) {
        StringBuilder lowerCaseChars = new StringBuilder();
        StringBuilder upperCaseChars = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isLowerCase(currentChar)) {
                lowerCaseChars.append(currentChar);
            } else if (Character.isUpperCase(currentChar)) {
                upperCaseChars.append(currentChar);
            }
        }
        return lowerCaseChars.toString() + upperCaseChars.toString();
    }

    private void checkFriendlyNumbers() {
        String num1Str = inputNumber1.getText().toString();
        String num2Str = inputNumber2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            resultText.setText("Please enter both numbers.");
            return;
        }

        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        if (areFriendly(num1, num2)) {
            resultText.setText(num1 + " and " + num2 + " are friendly numbers.");
        } else {
            resultText.setText(num1 + " and " + num2 + " are NOT friendly numbers.");
        }
    }

    private boolean areFriendly(int num1, int num2) {
        return sumOfDivisors(num1) == num2 && sumOfDivisors(num2) == num1;
    }

    private int sumOfDivisors(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    private void convertToHexadecimal() {
        String numStr = inputNumber3.getText().toString();

        if (numStr.isEmpty()) {
            convertedText.setText("Please enter a number.");
            return;
        }

        try {
            int decimal = Integer.parseInt(numStr, 16);
            convertedText.setText("Decimal: " + decimal);
        } catch (NumberFormatException e) {
            convertedText.setText("Invalid number!");
        }
    }

}

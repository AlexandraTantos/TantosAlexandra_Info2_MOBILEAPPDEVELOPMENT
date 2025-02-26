package com.example.ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private boolean isLightOn = false;
    private boolean isAutoMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageButton lightToggleBtn = findViewById(R.id.theme_btn);
        Switch autoModeSwitch = findViewById(R.id.auto_mode_switch);
        Spinner colorSpinner = findViewById(R.id.color_spinner);

        lightToggleBtn.setOnClickListener(view -> {
            if (!isAutoMode) {
                isLightOn = !isLightOn;
                updateLightMode(isLightOn ? R.color.yellow : R.color.dark_grey);
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Disable Auto Mode to use Manual Mode", Toast.LENGTH_LONG).show();
                });
            }
        });

        autoModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isAutoMode = isChecked;
            if (isChecked) {
                enableAutomaticMode();
            } else {
                updateLightMode(R.color.yellow);
            }
        });
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            Toast.makeText(MainActivity.this, "Test Toast", Toast.LENGTH_SHORT).show();
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {
                if (!isAutoMode) {
                    String selectedColor = parentView.getItemAtPosition(position).toString();
                    handleColorSelection(selectedColor);
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(MainActivity.this, "Disable Auto Mode to customize color", Toast.LENGTH_SHORT).show();
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void enableAutomaticMode() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 7 && hour < 18) {
            updateLightMode(R.color.white);
        } else if (hour >= 18 && hour < 22) {
            updateLightMode(R.color.orange);
            runOnUiThread(() -> {
                Toast.makeText(MainActivity.this, "Good evening! Do you need more light?", Toast.LENGTH_SHORT).show();
            });
        } else {
            updateLightMode(R.color.black);
            runOnUiThread(() -> {
                Toast.makeText(MainActivity.this, "Good night! The light has turned off automatically.", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void handleColorSelection(String selectedColor) {
        switch (selectedColor) {
            case "Relaxation":
                updateLightMode(R.color.blue);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Relaxation Mode (Blue)", Toast.LENGTH_LONG).show();
                });
                break;
            case "Alert Mode":
                updateLightMode(R.color.red);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Alert Mode (Red)", Toast.LENGTH_LONG).show();
                });
                break;
            case "Active":
                updateLightMode(R.color.green);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Active Mode (Green)", Toast.LENGTH_SHORT).show();
                });
                break;
            default:
                updateLightMode(R.color.white);
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Default Mode (White)", Toast.LENGTH_SHORT).show();
                });
                break;
        }
    }

    private void updateLightMode(int color) {
        findViewById(R.id.main).setBackgroundColor(getResources().getColor(color));
    }
}

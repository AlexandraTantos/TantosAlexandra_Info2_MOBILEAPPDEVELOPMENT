package com.example.ex2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean isDark = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ImageButton themeBtn = findViewById(R.id.theme_btn);
        Switch automaticSwitch = findViewById(R.id.auto_mode_switch);
        Spinner colorSpinner = findViewById(R.id.color_spinner);

        themeBtn.setOnClickListener(view ->{
            isDark = !isDark;
            if(isDark){
                themeBtn.setImageResource(R.drawable.ic_light);
                findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.dark_grey));
            } else {
                themeBtn.setImageResource(R.drawable.ic_dark);
                findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        automaticSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(isChecked){
                enableAutomaticMode();
            }else{
                findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.yellow));
            }
        }));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_arrays, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colorSpinner.setAdapter(adapter);

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, android.view.View selectedItemView, int position, long id) {

                String selectedColor = parentView.getItemAtPosition(position).toString();

                switch (selectedColor) {
                    case "Relaxation":
                        findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.blue));
                        Toast.makeText(MainActivity.this, "Relaxation Mode (Blue)", Toast.LENGTH_SHORT).show();
                        break;
                    case "Alert Mode":
                        findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.red));
                        Toast.makeText(MainActivity.this, "Alert Mode (Red)", Toast.LENGTH_SHORT).show();
                        break;
                    case "Active":
                        findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.green));
                        Toast.makeText(MainActivity.this, "Active Mode (Green)", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.white));
                        Toast.makeText(MainActivity.this, "Default Mode (White)", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void enableAutomaticMode(){
        TimePicker time = new TimePicker(this);
        if(time.getHour() > 7 && time.getHour() <18){
            findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.white));

        }else if(time.getHour() > 18 && time.getHour() < 22){
            findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.orange));
            Toast.makeText(this, "Good morning! Do you need more light?", Toast.LENGTH_SHORT).show();
        }else{
            findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.black));
            Toast.makeText(MainActivity.this, "Good night!The light has turned off automatically.", Toast.LENGTH_SHORT).show();
        }
    }
}
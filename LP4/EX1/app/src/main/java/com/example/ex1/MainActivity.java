package com.example.ex1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnCall = findViewById(R.id.btn_ic_call);
        btnCall.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Emergency Call Confirmation")
                    .setMessage("Are you sure you want to call emergency services (112)?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:112"));
                        startActivity(callIntent);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
        Button btnOpenWeb = findViewById(R.id.btn_open_web);
        btnOpenWeb.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(webIntent);
        });

        Button btnOpenActivity = findViewById(R.id.btn_open_activity);
        btnOpenActivity.setOnClickListener(v -> {
            Intent activityIntent = new Intent(this, SecondActivity.class);
            startActivity(activityIntent);
        });

    }
}
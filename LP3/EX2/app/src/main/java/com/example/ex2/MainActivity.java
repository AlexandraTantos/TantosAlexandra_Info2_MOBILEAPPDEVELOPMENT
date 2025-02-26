package com.example.ex2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView img;
    private Button startReadingButton, nextChapterButton;
    private TextView intro;
    private int currentChapter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        img = findViewById(R.id.book_cover);
        navigationView = findViewById(R.id.nav_view);
        startReadingButton = findViewById(R.id.start_reading_button);
        nextChapterButton = findViewById(R.id.next_chapter_button);
        intro = findViewById(R.id.book_intro);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.openDrawer, R.string.closeDrawer
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        startReadingButton.setOnClickListener(v -> {
            loadFragment(new Chapter1Fragment());
            startReadingButton.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            intro.setVisibility(View.GONE);
            nextChapterButton.setVisibility(View.VISIBLE);
        });

        nextChapterButton.setOnClickListener(v -> nextChapter());

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_chapter1) {
                loadFragment(new Chapter1Fragment());
                startReadingButton.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                intro.setVisibility(View.GONE);
                nextChapterButton.setVisibility(View.VISIBLE);
            } else if (id == R.id.nav_chapter2) {
                loadFragment(new Chapter2Fragment());
                startReadingButton.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                intro.setVisibility(View.GONE);
                nextChapterButton.setVisibility(View.VISIBLE);
            } else if (id == R.id.nav_chapter3) {
                loadFragment(new Chapter3Fragment());
                startReadingButton.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                intro.setVisibility(View.GONE);
                nextChapterButton.setVisibility(View.VISIBLE);
            }else{
                loadFragment(new Chapter4Fragment());
                startReadingButton.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
                intro.setVisibility(View.GONE);
                nextChapterButton.setVisibility(View.VISIBLE);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void nextChapter() {
        currentChapter++;
        Fragment fragment = null;

        switch (currentChapter) {
            case 2:
                fragment = new Chapter2Fragment();
                break;
            case 3:
                fragment = new Chapter3Fragment();
                break;
            case 4:
                fragment = new Chapter4Fragment();
                break;
            default:
                currentChapter = 1;
                fragment = new Chapter1Fragment();
                break;
        }

        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

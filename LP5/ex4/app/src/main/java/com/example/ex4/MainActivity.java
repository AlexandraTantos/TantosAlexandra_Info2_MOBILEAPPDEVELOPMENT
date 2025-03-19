package com.example.ex4;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Participant> participants = new ArrayList<>();
    private ArrayAdapter<Participant> adapter;
    private ListView participantListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        participantListView = findViewById(R.id.participantListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, participants);
        participantListView.setAdapter(adapter);

        loadParticipants();

        findViewById(R.id.addParticipantButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddParticipantDialog();
            }
        });

        Button sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortOptions();
            }
        });

        participantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showUpdateDeleteDialog(position);
            }
        });
    }

    private void showAddParticipantDialog() {
        final EditText firstNameEditText = new EditText(this);
        final EditText lastNameEditText = new EditText(this);
        final EditText scoreEditText = new EditText(this);

        firstNameEditText.setHint("First Name");
        lastNameEditText.setHint("Last Name");
        scoreEditText.setHint("Score (0-100)");

        TextView firstNameLabel = new TextView(this);
        firstNameLabel.setText("Enter First Name:");

        TextView lastNameLabel = new TextView(this);
        lastNameLabel.setText("Enter Last Name:");

        TextView scoreLabel = new TextView(this);
        scoreLabel.setText("Enter Score:");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(firstNameLabel);
        layout.addView(firstNameEditText);
        layout.addView(lastNameLabel);
        layout.addView(lastNameEditText);
        layout.addView(scoreLabel);
        layout.addView(scoreEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Participant")
                .setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String firstName = firstNameEditText.getText().toString();
                        String lastName = lastNameEditText.getText().toString();
                        String scoreText = scoreEditText.getText().toString();

                        if (!firstName.isEmpty() && !lastName.isEmpty() && !scoreText.isEmpty()) {
                            try {
                                int score = Integer.parseInt(scoreText);
                                if (score < 0 || score > 100) {
                                    Toast.makeText(MainActivity.this, "Score must be between 0 and 100", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Participant participant = new Participant(firstName, lastName, score);
                                participants.add(participant);
                                saveParticipants();
                                adapter.notifyDataSetChanged();
                            } catch (NumberFormatException e) {
                                Toast.makeText(MainActivity.this, "Please enter a valid number for score", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    private void showUpdateDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Action")
                .setMessage("Do you want to update or delete this participant?")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showUpdateDialog(position);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteParticipant(position);
                    }
                })
                .setNeutralButton("Cancel", null)
                .show();
    }

    private void showUpdateDialog(final int position) {
        final Participant participant = participants.get(position);

        final EditText firstNameEditText = new EditText(this);
        firstNameEditText.setText(participant.getFirstName());

        final EditText lastNameEditText = new EditText(this);
        lastNameEditText.setText(participant.getLastName());

        final EditText scoreEditText = new EditText(this);
        scoreEditText.setText(String.valueOf(participant.getScore()));

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(firstNameEditText);
        layout.addView(lastNameEditText);
        layout.addView(scoreEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Participant")
                .setView(layout)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        participant.setFirstName(firstNameEditText.getText().toString());
                        participant.setLastName(lastNameEditText.getText().toString());
                        participant.setScore(Integer.parseInt(scoreEditText.getText().toString()));

                        saveParticipants();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteParticipant(int position) {
        participants.remove(position);
        saveParticipants();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Participant deleted", Toast.LENGTH_SHORT).show();
    }

    private void saveParticipants() {
        SharedPreferences sharedPreferences = getSharedPreferences("participants", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("participantCount", participants.size());
        for (int i = 0; i < participants.size(); i++) {
            editor.putString("participant_" + i + "_firstName", participants.get(i).getFirstName());
            editor.putString("participant_" + i + "_lastName", participants.get(i).getLastName());
            editor.putInt("participant_" + i + "_score", participants.get(i).getScore());
        }

        editor.apply();
    }

    private void loadParticipants() {
        SharedPreferences sharedPreferences = getSharedPreferences("participants", MODE_PRIVATE);
        int participantCount = sharedPreferences.getInt("participantCount", 0);

        for (int i = 0; i < participantCount; i++) {
            String firstName = sharedPreferences.getString("participant_" + i + "_firstName", "");
            String lastName = sharedPreferences.getString("participant_" + i + "_lastName", "");
            int score = sharedPreferences.getInt("participant_" + i + "_score", 0);

            participants.add(new Participant(firstName, lastName, score));
        }

        adapter.notifyDataSetChanged();
    }

    private void showSortOptions() {
        final String[] sortOptions = {"Sort by Name (Asc)", "Sort by Name (Desc)", "Sort by Score (Asc)", "Sort by Score (Desc)"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort Participants")
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Collections.sort(participants, new Comparator<Participant>() {
                                    @Override
                                    public int compare(Participant p1, Participant p2) {
                                        return p1.getFirstName().compareTo(p2.getFirstName());
                                    }
                                });
                                break;
                            case 1:
                                Collections.sort(participants, new Comparator<Participant>() {
                                    @Override
                                    public int compare(Participant p1, Participant p2) {
                                        return p2.getFirstName().compareTo(p1.getFirstName());
                                    }
                                });
                                break;
                            case 2:
                                Collections.sort(participants, new Comparator<Participant>() {
                                    @Override
                                    public int compare(Participant p1, Participant p2) {
                                        return Integer.compare(p1.getScore(), p2.getScore());
                                    }
                                });
                                break;
                            case 3:
                                Collections.sort(participants, new Comparator<Participant>() {
                                    @Override
                                    public int compare(Participant p1, Participant p2) {
                                        return Integer.compare(p2.getScore(), p1.getScore());
                                    }
                                });
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

}
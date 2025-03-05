package com.example.ex3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameEditText, surnameEditText, phoneEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameEditText = findViewById(R.id.edit_contact_name);
        surnameEditText = findViewById(R.id.edit_contact_surname);
        phoneEditText = findViewById(R.id.edit_contact_phone);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String surname = surnameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            if (!name.isEmpty() && !surname.isEmpty() && !phone.isEmpty()) {
                Contact newContact = new Contact(name, surname, phone, "", "", "", 5);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_contact", newContact);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(AddContactActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

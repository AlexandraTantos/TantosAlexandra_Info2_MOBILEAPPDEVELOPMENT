package com.example.ex3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView contactName, contactPhone;
    private EditText editContactName, editContactPhone;
    private Button editButton;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ImageView imageView = findViewById(R.id.contact_image);
        contactName = findViewById(R.id.contact_name);
        contactPhone = findViewById(R.id.contact_phone);
        editContactName = findViewById(R.id.edit_contact_name);
        editContactPhone = findViewById(R.id.edit_contact_phone);
        editButton = findViewById(R.id.edit_button);

        contact = (Contact) getIntent().getSerializableExtra("contact");

        if (contact != null) {
            int imageResource = contact.getImageResourceId();
            Glide.with(this).load(imageResource).into(imageView);

            contactName.setText(contact.getName() + " " + contact.getSurname());
            contactPhone.setText(contact.getPhone());

            editContactName.setVisibility(View.GONE);
            editContactPhone.setVisibility(View.GONE);

            editButton.setOnClickListener(v -> {
                if (editButton.getText().toString().equals("Edit")) {
                    contactName.setVisibility(View.GONE);
                    contactPhone.setVisibility(View.GONE);
                    editContactName.setVisibility(View.VISIBLE);
                    editContactPhone.setVisibility(View.VISIBLE);
                    editContactName.setText(contact.getName());
                    editContactPhone.setText(contact.getPhone());
                    editButton.setText("Save");
                } else {
                    contact.setName(editContactName.getText().toString());
                    contact.setPhone(editContactPhone.getText().toString());

                    contactName.setText(contact.getName());
                    contactPhone.setText(contact.getPhone());
                    contactName.setVisibility(View.VISIBLE);
                    contactPhone.setVisibility(View.VISIBLE);
                    editContactName.setVisibility(View.GONE);
                    editContactPhone.setVisibility(View.GONE);
                    editButton.setText("Edit");
                }
            });
        }
    }
}

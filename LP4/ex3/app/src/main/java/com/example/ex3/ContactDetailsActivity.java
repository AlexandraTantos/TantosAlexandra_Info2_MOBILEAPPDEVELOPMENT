package com.example.ex3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class ContactDetailsActivity extends AppCompatActivity {

    private TextView contactName, contactPhone;
    private EditText editContactName, editContactPhone;
    private Button editButton, callButton;
    private Contact contact;
    private static final int REQUEST_CALL_PERMISSION = 1;

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
        callButton = findViewById(R.id.call_button);

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

            callButton.setOnClickListener(v -> makePhoneCall(contact.getPhone()));
        }
    }

    private void makePhoneCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (contact != null) {
                    makePhoneCall(contact.getPhone());
                }
            } else {
                Toast.makeText(this, "Permission denied to make calls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

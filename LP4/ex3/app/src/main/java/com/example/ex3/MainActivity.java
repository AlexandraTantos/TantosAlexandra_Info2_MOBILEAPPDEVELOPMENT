package com.example.ex3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_CONTACT = 1;

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactList = new ArrayList<>();
        contactList.add(new Contact("John", "Will", "+123456789", "john@example.com", "123 Street", "linkedin.com/in/john", R.drawable.img));
        contactList.add(new Contact("Alice", "Sam", "+987654321", "alice@example.com", "456 Street", "linkedin.com/in/alices", R.drawable.img_1));
        contactList.add(new Contact("Sam", "Audrey", "+123456789", "john@example.com", "123 Street", "linkedin.com/in/john", R.drawable.img_2));
        contactList.add(new Contact("Jenny", "Lopez", "+987654321", "alice@example.com", "456 Street", "linkedin.com/in/alices", R.drawable.img_3));
        contactList.add(new Contact("Michael", "Jack", "+123456789", "john@example.com", "123 Street", "linkedin.com/in/john", R.drawable.img_4));

        contactAdapter = new ContactAdapter(contactList, this::openContactDetails);
        recyclerView.setAdapter(contactAdapter);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivityForResult(intent, REQUEST_ADD_CONTACT);
        });
    }

    private void openContactDetails(Contact contact) {
        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_CONTACT && resultCode == RESULT_OK && data != null) {
            Contact newContact = (Contact) data.getSerializableExtra("new_contact");
            if (newContact != null) {
                contactList.add(newContact);
                contactAdapter.notifyItemInserted(contactList.size() - 1);
            }
        }
    }
}

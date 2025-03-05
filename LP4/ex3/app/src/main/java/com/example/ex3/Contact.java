package com.example.ex3;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name, surname, phone, email, address, linkedin, imageUrl;
    private int imageResourceId;

    public Contact(String firstName, String lastName, String phoneNumber, String email, String address, String linkedIn, int imageResourceId) {
        this.name = firstName;
        this.surname = lastName;
        this.phone = phoneNumber;
        this.email = email;
        this.address = address;
        this.linkedin = linkedIn;
        this.imageResourceId = imageResourceId;
    }
    public int getImageResourceId() {
        return imageResourceId;
    }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getLinkedin() { return linkedin; }
    public String getImageUrl() { return imageUrl; }
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}


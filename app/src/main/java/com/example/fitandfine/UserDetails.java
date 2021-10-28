package com.example.fitandfine;

public class UserDetails {

    String ID,Name,Email,Password,Phone;

    public UserDetails() {
    }

    public UserDetails(String password) {
        Password = password;
    }

    public UserDetails(String ID, String name, String email, String password, String phone) {
        this.ID = ID;
        Name = name;
        Email = email;
        Password = password;
        Phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}

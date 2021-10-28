package com.example.fitandfine.Ex_activities;

public class Model {

    String name,email,password,pone;

    public Model() {
    }

    public Model(String name, String email, String password, String pone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.pone = pone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPone() {
        return pone;
    }

    public void setPone(String pone) {
        this.pone = pone;
    }
}

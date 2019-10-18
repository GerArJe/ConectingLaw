package com.example.conectinglaw.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String lastname;
    private String email;
    private int cedula;
    private int telephoneNumber;
    private String photoUrl;

    public User() {
    }

    public User(String name, String lastname, String email, int
            cedula, int telephoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.cedula = cedula;
        this.telephoneNumber = telephoneNumber;
        this.photoUrl = "";
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getCedula() {
        return cedula;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

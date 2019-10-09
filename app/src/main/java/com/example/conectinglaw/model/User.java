package com.example.conectinglaw.model;

public class User {

    private String name;
    private String lastname;
    private String email;
    private int cedula;
    private int telephoneNumber;

    public User(String name, String lastname, String email, int
            cedula, int telephoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.cedula = cedula;
        this.telephoneNumber = telephoneNumber;
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
}

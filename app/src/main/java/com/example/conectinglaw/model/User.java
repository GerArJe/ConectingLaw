package com.example.conectinglaw.model;

public class User {

    private String name;
    private String email;
    private String cedula;
    private String clientType;

    public User(String name, String email, String cedula, String clientType) {
        this.name = name;
        this.email = email;
        this.cedula = cedula;
        this.clientType = clientType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCedula() {
        return cedula;
    }

    public String getClientType() {
        return clientType;
    }
}

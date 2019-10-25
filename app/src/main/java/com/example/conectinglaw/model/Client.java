package com.example.conectinglaw.model;

import java.io.Serializable;

public class Client extends User implements Serializable {

    public Client() {
    }

    public Client(String name, String lastname,
                  String email, int cedula, int telephoneNumber) {
        super(name, lastname, email, cedula, telephoneNumber);
    }
}

package com.example.conectinglaw.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;


public class Chat implements Serializable{
    private String idUser;
    private String message;
    private com.google.firebase.Timestamp time;

    public Chat() {
    }

    public Chat(String idUser, String message, com.google.firebase.Timestamp time) {
        this.idUser = idUser;
        this.message = message;
        this.time = time;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTime() {
        return time;
    }
}

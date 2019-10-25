package com.example.conectinglaw.model;

public class Chat {
    private String idUser;
    private String message;

    public Chat() {
    }

    public Chat(String idUser, String message) {
        this.idUser = idUser;
        this.message = message;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getMessage() {
        return message;
    }
}

package com.example.conectinglaw.model;
import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {
    private String idUser;
    private String message;
    private String time;

    public Chat() {
    }

    public Chat(String idUser, String message, String time) {
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

    public String getTime() {
        return time;
    }
}

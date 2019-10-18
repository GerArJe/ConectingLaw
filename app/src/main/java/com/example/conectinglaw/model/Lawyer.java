package com.example.conectinglaw.model;

import java.io.Serializable;
import java.util.List;

public class Lawyer extends User implements Serializable {

    private List<String> topicsWork;

    public Lawyer() {
    }

    public Lawyer(String name, String lastname, String email,
                  int cedula, int telephoneNumber,
                  List<String> topicsWork) {
        super(name, lastname, email, cedula, telephoneNumber);
        this.topicsWork = topicsWork;
    }

    public List<String> getTopicsWork() {
        return topicsWork;
    }

    public void setTopicsWork(List<String> topicsWork) {
        this.topicsWork = topicsWork;
    }
}

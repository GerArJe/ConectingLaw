package com.example.conectinglaw.model;

import java.util.List;

public class Lawyer extends User {

    private List<String> topicsWork;

    public Lawyer(String name, String lastname, String email, int cedula,
                  int telephoneNumber, List<String> topicsWork) {
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

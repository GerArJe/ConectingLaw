package com.example.conectinglaw.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lawyer extends User implements Serializable {

    private boolean penal;
    private boolean civil;
    private boolean mercantil;

    public Lawyer() {
    }

    public Lawyer(String name, String lastname, String email,
                  int cedula, int telephoneNumber, boolean penal,
                  boolean civil, boolean mercantil) {
        super(name, lastname, email, cedula, telephoneNumber);
        this.penal = penal;
        this.civil = civil;
        this.mercantil = mercantil;
    }

    public boolean isPenal() {
        return penal;
    }

    public void setPenal(boolean penal) {
        this.penal = penal;
    }

    public boolean isCivil() {
        return civil;
    }

    public void setCivil(boolean civil) {
        this.civil = civil;
    }

    public boolean isMercantil() {
        return mercantil;
    }

    public void setMercantil(boolean mercantil) {
        this.mercantil = mercantil;
    }

    public String tipoAbogado(){
        ArrayList<String> cadena = new ArrayList<>();
        if (penal){
            cadena.add("Penal");
        }
        if (mercantil){
            cadena.add("Mercantil");
        }
        if (civil){
            cadena.add("Civil");
        }
        return cadena.toString();
    }
}

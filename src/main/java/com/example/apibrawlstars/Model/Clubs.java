package com.example.apibrawlstars.Model;

public class Clubs {
    private String tag;
    private String nombre;
    private String descripcion;
    private String tipo;
    private int badgedID;
    private int requiredTrophies;
    private int trophiesM;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getBadgedID() {
        return badgedID;
    }

    public void setBadgedID(int badgedID) {
        this.badgedID = badgedID;
    }

    public int getRequiredTrophies() {
        return requiredTrophies;
    }

    public void setRequiredTrophies(int requiredTrophies) {
        this.requiredTrophies = requiredTrophies;
    }

    public int getTrophiesM() {
        return trophiesM;
    }

    public void setTrophiesM(int trophiesM) {
        this.trophiesM = trophiesM;
    }
}

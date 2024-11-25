package com.example.apibrawlstars.Model;

public class Clubs {

    private String tag; // PRIMARY KEY
    private String nombre;
    private String descripcion;
    private String tipo;
    private int badgeId;
    private int requiredTrophies;
    private int trophies;

    public Clubs(String tag, String nombre, String descripcion, String tipo, int badgeId, int requiredTrophies, int trophies) {
        this.tag = tag;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.badgeId = badgeId;
        this.requiredTrophies = requiredTrophies;
        this.trophies = trophies;
    }


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

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public int getRequiredTrophies() {
        return requiredTrophies;
    }

    public void setRequiredTrophies(int requiredTrophies) {
        this.requiredTrophies = requiredTrophies;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    @Override
    public String toString() {
        return "Clubs{" +
                "tag='" + tag + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", badgeId=" + badgeId +
                ", requiredTrophies=" + requiredTrophies +
                ", trophies=" + trophies +
                '}';
    }
}

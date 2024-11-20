package com.example.apibrawlstars.Model;

public class Brawler {
 private String playerTag;
 private int id;
 private String nameBrawler;
 private int power;
 private int nRank;
 private int trophies;
 private int highestTrophies;

    public String getPlayerTag() {
        return playerTag;
    }

    public void setPlayerTag(String playerTag) {
        this.playerTag = playerTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBrawler() {
        return nameBrawler;
    }

    public void setNameBrawler(String nameBrawler) {
        this.nameBrawler = nameBrawler;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public int getHighestTrophies() {
        return highestTrophies;
    }

    public void setHighestTrophies(int highestTrophies) {
        this.highestTrophies = highestTrophies;
    }

    public int getnRank() {
        return nRank;
    }

    public void setnRank(int nRank) {
        this.nRank = nRank;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

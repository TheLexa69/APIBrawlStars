package com.example.apibrawlstars.Model;

public class Gears {
    private int id;
    private String name;
    private int level;
    private String playerTag;
    private int brawlerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlayerTag() {
        return playerTag;
    }

    public void setPlayerTag(String playerTag) {
        this.playerTag = playerTag;
    }

    public int getBrawlerId() {
        return brawlerId;
    }

    public void setBrawlerId(int brawlerId) {
        this.brawlerId = brawlerId;
    }
}

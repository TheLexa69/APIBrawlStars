package com.example.apibrawlstars.Model;

public class Battles {
    private String battleTime;
    private String playerTag;
    private String battleMode;
    private int trophyChange;
    private String result;
    private int duration;


    public Battles(String battleTime, String battleMode, int trophyChange, String result, int duration) {
        this.battleTime = battleTime;
        this.battleMode = battleMode;
        this.trophyChange = trophyChange;
        this.result = result;
        this.duration = duration;
    }

    // Constructor
    public Battles(String battleTime, String playerTag, String battleMode, int trophyChange, String result, int duration) {
        this.battleTime = battleTime;
        this.playerTag = playerTag;
        this.battleMode = battleMode;
        this.trophyChange = trophyChange;
        this.result = result;
        this.duration = duration;
    }

    // Getters and Setters

    public String getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(String battleTime) {
        this.battleTime = battleTime;
    }

    public String getPlayerTag() {
        return playerTag;
    }

    public void setPlayerTag(String playerTag) {
        this.playerTag = playerTag;
    }

    public String getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(String battleMode) {
        this.battleMode = battleMode;
    }

    public int getTrophyChange() {
        return trophyChange;
    }

    public void setTrophyChange(int trophyChange) {
        this.trophyChange = trophyChange;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Battles{" +
                "battleTime='" + battleTime + '\'' +
                ", playerTag='" + playerTag + '\'' +
                ", battleMode='" + battleMode + '\'' +
                ", trophyChange=" + trophyChange +
                ", result='" + result + '\'' +
                ", duration=" + duration +
                '}';
    }
}
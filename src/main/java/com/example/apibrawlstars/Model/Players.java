package com.example.apibrawlstars.Model;

public class Players {
    private String tag;
    private String name;
    private String nameColor;
    private int iconId;
    private int trophies;
    private int highestTrophies;
    private int expLevel;
    private int expPoints;
    private boolean isQualifiedFromChampChallenge;
    private int threeVsThreeVictories;
    private int soloVictories;
    private int duoVictories;
    private int bestRoboRumbleTime;
    private int bestTimeAsBigBrawler;
    private String clubTag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public int getTrophies() {
        return trophies;
    }

    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getHighestTrophies() {
        return highestTrophies;
    }

    public void setHighestTrophies(int highestTrophies) {
        this.highestTrophies = highestTrophies;
    }

    public int getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(int expLevel) {
        this.expLevel = expLevel;
    }

    public boolean isQualifiedFromChampChallenge() {
        return isQualifiedFromChampChallenge;
    }

    public void setQualifiedFromChampChallenge(boolean qualifiedFromChampChallenge) {
        isQualifiedFromChampChallenge = qualifiedFromChampChallenge;
    }

    public int getSoloVictories() {
        return soloVictories;
    }

    public void setSoloVictories(int soloVictories) {
        this.soloVictories = soloVictories;
    }

    public int getDuoVictories() {
        return duoVictories;
    }

    public void setDuoVictories(int duoVictories) {
        this.duoVictories = duoVictories;
    }

    public int getBestTimeAsBigBrawler() {
        return bestTimeAsBigBrawler;
    }

    public void setBestTimeAsBigBrawler(int bestTimeAsBigBrawler) {
        this.bestTimeAsBigBrawler = bestTimeAsBigBrawler;
    }

    public String getClubTag() {
        return clubTag;
    }

    public void setClubTag(String clubTag) {
        this.clubTag = clubTag;
    }

    public int getBestRoboRumbleTime() {
        return bestRoboRumbleTime;
    }

    public void setBestRoboRumbleTime(int bestRoboRumbleTime) {
        this.bestRoboRumbleTime = bestRoboRumbleTime;
    }

    public int getThreeVsThreeVictories() {
        return threeVsThreeVictories;
    }

    public void setThreeVsThreeVictories(int threeVsThreeVictories) {
        this.threeVsThreeVictories = threeVsThreeVictories;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }
}

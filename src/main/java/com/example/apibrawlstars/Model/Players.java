package com.example.apibrawlstars.Model;

public class Players {
    private String tag; // PRIMARY KEY
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

    //Parametrized
    public Players(String tag, String name, int trophies, int highestTrophies,
                   int soloVictories, int duoVictories, String clubTag,
                   int threeVsThreeVictories) {
        this.tag = tag;
        this.name = name;
        this.trophies = trophies;
        this.highestTrophies = highestTrophies;
        this.soloVictories = soloVictories;
        this.duoVictories = duoVictories;
        this.clubTag = clubTag;
        this.threeVsThreeVictories = threeVsThreeVictories;
    }

    // Constructor
    public Players(String tag, String name, String nameColor, int iconId, int trophies,
                   int highestTrophies, int expLevel, int expPoints,
                   boolean isQualifiedFromChampChallenge, int threeVsThreeVictories,
                   int soloVictories, int duoVictories, int bestRoboRumbleTime,
                   int bestTimeAsBigBrawler, String clubTag) {
        this.tag = tag;
        this.name = name;
        this.nameColor = nameColor;
        this.iconId = iconId;
        this.trophies = trophies;
        this.highestTrophies = highestTrophies;
        this.expLevel = expLevel;
        this.expPoints = expPoints;
        this.isQualifiedFromChampChallenge = isQualifiedFromChampChallenge;
        this.threeVsThreeVictories = threeVsThreeVictories;
        this.soloVictories = soloVictories;
        this.duoVictories = duoVictories;
        this.bestRoboRumbleTime = bestRoboRumbleTime;
        this.bestTimeAsBigBrawler = bestTimeAsBigBrawler;
        this.clubTag = clubTag;
    }

    // Getters and Setters
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

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
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

    public int getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(int expLevel) {
        this.expLevel = expLevel;
    }

    public int getExpPoints() {
        return expPoints;
    }

    public void setExpPoints(int expPoints) {
        this.expPoints = expPoints;
    }

    public boolean isQualifiedFromChampChallenge() {
        return isQualifiedFromChampChallenge;
    }

    public void setQualifiedFromChampChallenge(boolean qualifiedFromChampChallenge) {
        isQualifiedFromChampChallenge = qualifiedFromChampChallenge;
    }

    public int getThreeVsThreeVictories() {
        return threeVsThreeVictories;
    }

    public void setThreeVsThreeVictories(int threeVsThreeVictories) {
        this.threeVsThreeVictories = threeVsThreeVictories;
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

    public int getBestRoboRumbleTime() {
        return bestRoboRumbleTime;
    }

    public void setBestRoboRumbleTime(int bestRoboRumbleTime) {
        this.bestRoboRumbleTime = bestRoboRumbleTime;
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

    // toString method for easy representation
    @Override
    public String toString() {
        return "Players{" +
                "tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", nameColor='" + nameColor + '\'' +
                ", iconId=" + iconId +
                ", trophies=" + trophies +
                ", highestTrophies=" + highestTrophies +
                ", expLevel=" + expLevel +
                ", expPoints=" + expPoints +
                ", isQualifiedFromChampChallenge=" + isQualifiedFromChampChallenge +
                ", threeVsThreeVictories=" + threeVsThreeVictories +
                ", soloVictories=" + soloVictories +
                ", duoVictories=" + duoVictories +
                ", bestRoboRumbleTime=" + bestRoboRumbleTime +
                ", bestTimeAsBigBrawler=" + bestTimeAsBigBrawler +
                ", clubTag='" + clubTag + '\'' +
                '}';
    }
}
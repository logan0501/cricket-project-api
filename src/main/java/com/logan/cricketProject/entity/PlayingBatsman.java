package com.logan.cricketProject.entity;

public class PlayingBatsman {
    private String batsmanId;
    private int runs;
    private int ballsFaced;
    private String dismissedById;

    public PlayingBatsman(String batsmanId) {
        this.batsmanId = batsmanId;
    }

    public String getBatsmanId() {
        return batsmanId;
    }

    public int getRuns() {
        return runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public String getDismissedById() {
        return dismissedById;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public void setDismissedById(String dismissedById) {
        this.dismissedById = dismissedById;
    }

    @Override
    public String toString() {
        return "PlayingBatsman{" +
                "batsmanId='" + batsmanId + '\'' +
                ", runs=" + runs +
                ", ballsFaced=" + ballsFaced +
                ", dismissedById='" + dismissedById + '\'' +
                '}';
    }
}

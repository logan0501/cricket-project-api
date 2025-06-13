package com.logan.cricketProject.entity;

import java.util.ArrayList;
import java.util.List;

public class PlayingBowler {
    private String bowlerId;
    private int numberOfOversBowled;
    private int numberOfRunsGiven;
    private List<String> wicketsTakenIds;

    public PlayingBowler(String bowlerId) {
        this.bowlerId = bowlerId;
        wicketsTakenIds = new ArrayList<>();
    }

    public String getBowlerId() {
        return bowlerId;
    }

    public int getNumberOfOversBowled() {
        return numberOfOversBowled;
    }

    public int getNumberOfRunsGiven() {
        return numberOfRunsGiven;
    }

    public List<String> getWicketsTakenIds() {
        return wicketsTakenIds;
    }

    public void setNumberOfOversBowled(int numberOfOversBowled) {
        this.numberOfOversBowled = numberOfOversBowled;
    }

    public void setNumberOfRunsGiven(int numberOfRunsGiven) {
        this.numberOfRunsGiven = numberOfRunsGiven;
    }

    public void addWicket(String batsmanId) {
        wicketsTakenIds.add(batsmanId);
    }

    @Override
    public String toString() {
        return "PlayingBowler{" +
                "bowlerId='" + bowlerId + '\'' +
                ", numberOfOversBowled=" + numberOfOversBowled +
                ", numberOfRunsGiven=" + numberOfRunsGiven +
                ", wicketsTakenIds=" + wicketsTakenIds +
                '}';
    }
}


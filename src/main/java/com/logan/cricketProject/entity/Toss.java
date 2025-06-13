package com.logan.cricketProject.entity;

import com.logan.cricketProject.enums.TossDecision;

import java.util.Random;

public class Toss {
    private String wonByTeamId;
    private TossDecision tossDecision;

    private Toss(String wonByTeamId, TossDecision tossDecision) {
        this.wonByTeamId = wonByTeamId;
        this.tossDecision = tossDecision;
    }

    public static Toss simulate(String team1Id, String team2Id){
        Random random = new Random();

        String[] tossTeamChoices = {team1Id, team2Id};
        String tossWinner = tossTeamChoices[random.nextInt(tossTeamChoices.length)];

        TossDecision tossDecision = TossDecision.values()[random.nextInt(TossDecision.values().length)];

        return new Toss(tossWinner,tossDecision);
    }

    public String getWonByTeamId() {
        return wonByTeamId;
    }

    public TossDecision getTossDecision() {
        return tossDecision;
    }

    @Override
    public String toString() {
        return "Toss won by teamId: " + wonByTeamId + ", Decision: " + tossDecision;
    }
}

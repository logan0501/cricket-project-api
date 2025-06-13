package com.logan.cricketProject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "matches")
public class Match {
    @Id
    private String matchId;

    private String team1Id;
    private String team2Id;
    private List<String> team1Playing11;
    private List<String> team2Playing11;
    private int numberOfOvers;
    Innings innings1;
    Innings innings2;
    private String winnerTeamId;
    private String matchResult;

    public int getNumberOfOvers() {
        return numberOfOvers;
    }

    public void setNumberOfOvers(int numberOfOvers) {
        this.numberOfOvers = numberOfOvers;
    }

    private Toss toss;


    public Match() {
    }

    public Match(String team1Id, String team2Id, List<String> team1Playing11, List<String> team2Playing11) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Playing11 = team1Playing11;
        this.team2Playing11 = team2Playing11;
        this.numberOfOvers = 3;
    }

    public Match(String team1Id, String team2Id, List<String> team1Playing11, List<String> team2Playing11, int numberOfOvers) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Playing11 = team1Playing11;
        this.team2Playing11 = team2Playing11;
        this.numberOfOvers = numberOfOvers;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public Toss getToss() {
        return toss;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public List<String> getTeam1Playing11() {
        return team1Playing11;
    }

    public void setTeam1Playing11(List<String> team1Playing11) {
        this.team1Playing11 = team1Playing11;
    }

    public List<String> getTeam2Playing11() {
        return team2Playing11;
    }

    public void setTeam2Playing11(List<String> team2Playing11) {
        this.team2Playing11 = team2Playing11;
    }

    public void setToss(Toss toss) {
        this.toss = toss;
    }

    public void setInnings1(Innings innings1) {
        this.innings1 = innings1;
    }

    public void setInnings2(Innings innings2) {
        this.innings2 = innings2;
    }

    public void decideWinner() {
        String team1Id = innings1.getBattingTeamId();
        String team2Id = innings1.getBowlingTeamId();

        matchResult = "TIE";
        int target = innings1.getRuns();
        if (target < innings2.getRuns()) {
            winnerTeamId = team2Id;
            matchResult = team2Id + " won by " + (10 - innings2.getWickets()) + " wickets.";
        } else if (target > innings2.getRuns()) {
            winnerTeamId = team1Id;
            matchResult = team1Id + " won by " + (target - innings2.getRuns()) + " runs.";
        } else {
            winnerTeamId = null;
        }
    }

    public Innings getInnings1() {
        return innings1;
    }

    public Innings getInnings2() {
        return innings2;
    }

    public String getWinnerTeamId() {
        return winnerTeamId;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setWinnerTeamId(String winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public void setMatchResult(String matchResult) {
        this.matchResult = matchResult;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId='" + matchId + '\'' +
                ", team1Id='" + team1Id + '\'' +
                ", team2Id='" + team2Id + '\'' +
                ", team1Playing11=" + team1Playing11 +
                ", team2Playing11=" + team2Playing11 +
                ", numberOfOvers=" + numberOfOvers +
                ", innings1=" + innings1 +
                ", innings2=" + innings2 +
                ", winnerTeamId='" + winnerTeamId + '\'' +
                ", matchResult='" + matchResult + '\'' +
                ", toss=" + toss +
                '}';
    }
}

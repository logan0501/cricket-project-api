package com.logan.cricketProject.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Innings {
    private String battingTeamId;
    private List<PlayingBatsman> playingBatsmen = new ArrayList<>();
    private String bowlingTeamId;
    private List<PlayingBowler> playingBowlers = new ArrayList<>();

    private List<List<Character>> oversLog;
    private int runs;
    private int wickets;
    private static int numberOfOvers;

    public String getBattingTeamId() {
        return battingTeamId;
    }

    public List<PlayingBatsman> getPlayingBatsmen() {
        return playingBatsmen;
    }

    public String getBowlingTeamId() {
        return bowlingTeamId;
    }

    public List<PlayingBowler> getPlayingBowlers() {
        return playingBowlers;
    }

    public List<List<Character>> getOversLog() {
        return oversLog;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public static void setNumberOfOvers(int numberOfOvers) {
        Innings.numberOfOvers = numberOfOvers;
    }

    public Innings(String battingTeamId, List<String> battersId, String bowlingTeamId, List<String> bowlersId) {
        this.battingTeamId = battingTeamId;
        for (String batterId : battersId) {
            playingBatsmen.add(new PlayingBatsman(batterId));
        }
        this.bowlingTeamId = bowlingTeamId;
        for (String bowlerId : bowlersId) {
            playingBowlers.add(new PlayingBowler(bowlerId));
        }
        this.oversLog = new ArrayList<>();
    }

    private List<Character> simulateOver(PlayingBowler currentBowler, boolean isSecondInnings, int target) {
        char[] ballChoices = {'0', '1', '2', '3', '4', '6', 'W'};
        int numberOfBalls = 6;
        Random random = new Random();
        List<Character> currentOverLog = new ArrayList<>();
        PlayingBatsman currentBatsman = playingBatsmen.get(wickets);


        while (numberOfBalls > 0 && wickets < playingBatsmen.size()) {
            char currentBall = ballChoices[random.nextInt(ballChoices.length)];
            currentOverLog.add(currentBall);
            currentBatsman.setBallsFaced(currentBatsman.getBallsFaced() + 1);

            if (currentBall == 'W') {
                this.wickets++;
                currentBatsman.setDismissedById(currentBowler.getBowlerId());
                currentBowler.addWicket(currentBatsman.getBatsmanId());

                if (wickets >= playingBatsmen.size()) {
                    break;
                }
                currentBatsman = playingBatsmen.get(wickets);
            } else {
                int run = Character.getNumericValue(currentBall);
                this.runs += run;
                currentBatsman.setRuns(currentBatsman.getRuns() + run);
                currentBowler.setNumberOfRunsGiven(currentBowler.getNumberOfRunsGiven() + run);
                if (isSecondInnings && target < runs) {
                    break;
                }
            }
            numberOfBalls--;
        }

        return currentOverLog;
    }

    public void simulateInnings(boolean isSecondInnings, int target) {

        int currentOver = 1;
        while (currentOver <= numberOfOvers && wickets < playingBatsmen.size()) {
            PlayingBowler currentBowler = playingBowlers.get((currentOver - 1) % playingBowlers.size());
            List<Character> overLog = simulateOver(currentBowler, isSecondInnings, target);
            oversLog.add(overLog);
            currentBowler.setNumberOfOversBowled(currentBowler.getNumberOfOversBowled() + 1);
            if (isSecondInnings && target < runs) {
                break;
            }
            currentOver++;
        }

        playingBatsmen = playingBatsmen.stream().filter(batsman -> batsman.getBallsFaced() > 0).toList();
        playingBowlers = playingBowlers.stream().filter(bowler -> bowler.getNumberOfOversBowled() > 0).toList();
    }

    @Override
    public String toString() {
        return "Innings{" +
                "battingTeamId='" + battingTeamId + '\'' +
                ", playingBatsmen=" + playingBatsmen +
                ", bowlingTeamId='" + bowlingTeamId + '\'' +
                ", playingBowlers=" + playingBowlers +
                ", oversLog=" + oversLog +
                ", runs=" + runs +
                ", wickets=" + wickets +
                '}';
    }
}
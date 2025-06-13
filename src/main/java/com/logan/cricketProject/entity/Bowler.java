package com.logan.cricketProject.entity;

import com.logan.cricketProject.enums.PlayerType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Bowler extends Player{
    private int currentWickets = 0;

    public Bowler() {
        super();
    }

    public Bowler(String playerName, String teamId, boolean isPlaying) {
        super( playerName, teamId, isPlaying, PlayerType.BOWLER);
    }

    public int getCurrentWickets() {
        return currentWickets;
    }

    public void setCurrentWickets(int currentWickets) {
        this.currentWickets = currentWickets;
    }
}

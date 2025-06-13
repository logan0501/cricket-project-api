package com.logan.cricketProject.entity;

import com.logan.cricketProject.enums.PlayerType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "players")
public class Player {

    @Id
    private String playerId;

    private String playerName;
    private String teamId;
    private boolean isPlaying;

    @Field("playerType")
    private PlayerType playerType;

    private int currentScore = 0;


    public Player() {
    }

    public Player(String playerName, String teamId, boolean isPlaying, PlayerType playerType) {
        this.playerName = playerName;
        this.teamId = teamId;
        this.isPlaying = isPlaying;
        this.playerType = playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }
}

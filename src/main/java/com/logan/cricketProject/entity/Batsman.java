package com.logan.cricketProject.entity;

import com.logan.cricketProject.enums.PlayerType;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Batsman extends Player {

    public Batsman() {
        super();
    }

    public Batsman(String playerName, String teamId) {
        super(playerName, teamId, false, PlayerType.BATSMAN);
    }

    public Batsman(String playerName, String teamId, boolean isPlaying) {
        super(playerName, teamId, isPlaying, PlayerType.BATSMAN);
    }

}

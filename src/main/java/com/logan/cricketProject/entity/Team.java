package com.logan.cricketProject.entity;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "teams")
public class Team {
    @Id
    private String teamId;
    @NotBlank(message = "Team name cannot be empty")
    private String teamName;
    private List<String> playerList = new ArrayList<>();
    private List<String> playing11 = new ArrayList<>();

    public Team() {
    }

    public Team(String teamName, List<String> playerList, List<String> playing11) {
        this.teamName = teamName;
        this.playerList = playerList;
        this.playing11 = playing11;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<String> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<String> playerList) {
        this.playerList = playerList;
    }

    public List<String> getPlaying11() {
        return playing11;
    }

    public void setPlaying11(List<String> playing11) {
        this.playing11 = playing11;
    }
}

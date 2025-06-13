package com.logan.cricketProject.responseDTO;

import com.logan.cricketProject.entity.Team;

import java.util.List;

public class TeamResponse {
    List<Team> teams;

    public TeamResponse(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}

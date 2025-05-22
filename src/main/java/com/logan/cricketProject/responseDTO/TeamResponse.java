package com.logan.cricketProject.responseDTO;

import com.logan.cricketProject.entity.TeamDAO;

import java.util.List;

public class TeamResponse {
    List<TeamDAO> teams;

    public TeamResponse(List<TeamDAO> teams) {
        this.teams = teams;
    }

    public List<TeamDAO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDAO> teams) {
        this.teams = teams;
    }
}

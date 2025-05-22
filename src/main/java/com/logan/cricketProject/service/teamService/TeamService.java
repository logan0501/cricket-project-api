package com.logan.cricketProject.service.teamService;

import com.logan.cricketProject.entity.TeamDAO;

import java.util.List;

public interface TeamService {
    List<TeamDAO> findAllTeams();

    TeamDAO addNewTeam(TeamDAO teamDAO);

    TeamDAO updateTeam(TeamDAO teamDAO);

    void deleteTeamById(String id);
}

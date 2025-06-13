package com.logan.cricketProject.service.teamService;

import com.logan.cricketProject.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAllTeams();

    Team addNewTeam(Team team);

    Team updateTeam(Team team);

    void deleteTeamById(String id);

    boolean teamExistById(String id);

    Optional<Team> findById(String id);
}

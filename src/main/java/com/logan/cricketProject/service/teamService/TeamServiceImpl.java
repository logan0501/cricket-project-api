package com.logan.cricketProject.service.teamService;

import com.logan.cricketProject.entity.Team;
import com.logan.cricketProject.exceptions.ResourceNotFoundException;
import com.logan.cricketProject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public Team addNewTeam(Team team) {
        return teamRepository.save(team);
    }

    private void checkTeamExists(String updateTeamId) {
        if (updateTeamId == null || !teamRepository.existsById(updateTeamId)) {
            throw new ResourceNotFoundException("Team with ID " + updateTeamId + " does not exist");
        }
    }

    @Override
    public Team updateTeam(Team team) {
        String updateTeamId = team.getTeamId();
        checkTeamExists(updateTeamId);
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeamById(String id) {
        checkTeamExists(id);
        teamRepository.deleteById(id);
    }

    @Override
    public boolean teamExistById(String id) {
        return teamRepository.existsById(id);
    }

    @Override
    public Optional<Team> findById(String id) {
        return teamRepository.findById(id);
    }
}

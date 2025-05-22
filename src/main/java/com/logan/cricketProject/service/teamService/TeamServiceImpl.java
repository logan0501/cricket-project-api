package com.logan.cricketProject.service.teamService;

import com.logan.cricketProject.entity.TeamDAO;
import com.logan.cricketProject.exceptions.ResourceNotFoundException;
import com.logan.cricketProject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDAO> findAllTeams() {
        return teamRepository.findAll();
    }

    public TeamDAO addNewTeam(TeamDAO teamDAO) {
        return teamRepository.save(teamDAO);
    }

    private void checkTeamExists(String updateTeamId) {
        if (updateTeamId == null || !teamRepository.existsById(updateTeamId)) {
            throw new ResourceNotFoundException("Team with ID " + updateTeamId + " does not exist");
        }
    }

    @Override
    public TeamDAO updateTeam(TeamDAO teamDAO) {
        String updateTeamId = teamDAO.getTeamId();
        checkTeamExists(updateTeamId);
        return teamRepository.save(teamDAO);
    }

    @Override
    public void deleteTeamById(String id) {
        checkTeamExists(id);
        teamRepository.deleteById(id);
    }
}

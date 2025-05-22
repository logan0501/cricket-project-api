package com.logan.cricketProject.controller.teamController;

import com.logan.cricketProject.entity.TeamDAO;
import com.logan.cricketProject.responseDTO.SuccessResponse;
import com.logan.cricketProject.responseDTO.TeamResponse;
import com.logan.cricketProject.service.teamService.TeamServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {
    private final TeamServiceImpl teamService;

    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public ResponseEntity<TeamResponse> getAllTeams() {
        List<TeamDAO> teams = teamService.findAllTeams();
        return ResponseEntity.ok(new TeamResponse(teams));
    }

    @PostMapping("/teams")
    public TeamDAO addNewTeam(@Valid @RequestBody TeamDAO team) {
        return teamService.addNewTeam(team);
    }

    @PutMapping("/teams")
    public TeamDAO updateTeam(@Valid @RequestBody TeamDAO team) {
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<SuccessResponse> deleteTeam(@PathVariable String teamId) {
        teamService.deleteTeamById(teamId);
        return ResponseEntity.ok(new SuccessResponse("Team deleted successfully"));
    }
}

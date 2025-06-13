package com.logan.cricketProject.controller.teamController;

import com.logan.cricketProject.entity.Player;
import com.logan.cricketProject.entity.Team;
import com.logan.cricketProject.responseDTO.SuccessResponse;
import com.logan.cricketProject.responseDTO.TeamResponse;
import com.logan.cricketProject.service.playerService.PlayerService;
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
    private final PlayerService playerService;


    @Autowired
    public TeamController(TeamServiceImpl teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/teams")
    public ResponseEntity<TeamResponse> getAllTeams() {
        List<Team> teams = teamService.findAllTeams();
        return ResponseEntity.ok(new TeamResponse(teams));
    }

    @PostMapping("/teams")
    public Team addNewTeam(@Valid @RequestBody Team team) {
        return teamService.addNewTeam(team);
    }

    @PutMapping("/teams")
    public Team updateTeam(@Valid @RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<SuccessResponse> deleteTeam(@PathVariable String teamId) {
        teamService.deleteTeamById(teamId);
        return ResponseEntity.ok(new SuccessResponse("Team deleted successfully"));
    }

    @GetMapping("/teams/{teamId}/auto-playing11")
    public List<Player> getAutoPlaying11(@PathVariable String teamId) {
        return playerService.getAutoPlaying11(teamId);
    }
}

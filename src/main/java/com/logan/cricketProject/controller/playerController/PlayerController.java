package com.logan.cricketProject.controller.playerController;

import com.logan.cricketProject.entity.Batsman;
import com.logan.cricketProject.entity.Bowler;
import com.logan.cricketProject.entity.Player;
import com.logan.cricketProject.enums.PlayerType;
import com.logan.cricketProject.service.playerService.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    private final PlayerServiceImpl playerService;

    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/players/{playerId}")
    public Player getPlayerById(@PathVariable String playerId) {
        return playerService.findPlayerById(playerId);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayerById(@PathVariable String playerId, @RequestBody Player player) {
        return playerService.updatePlayer(playerId, player);
    }

    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Player deleted successfully");
    }


    @GetMapping("/players/batsmen")
    public List<Player> getAllBatsmen() {
        return playerService.findByPlayerType(PlayerType.BATSMAN);
    }

    @PostMapping("/players/batsmen")
    public List<Player> createNewBatsman(@RequestBody List<Batsman> batsmen) {
        for (Batsman batsman : batsmen) {
            batsman.setPlayerType(PlayerType.BATSMAN);
        }
        List<Player> players = new ArrayList<>(batsmen);
        return playerService.createPlayers(players);
    }

    @GetMapping("/players/bowlers")
    public List<Player> getAllBowlers() {
        return playerService.findByPlayerType(PlayerType.BOWLER);
    }

    @PostMapping("/players/bowlers")
    public List<Player> createNewBowler(@RequestBody List<Bowler> bowlers) {
        for (Bowler bowler : bowlers) {
            bowler.setPlayerType(PlayerType.BOWLER);
        }
        List<Player> players = new ArrayList<>(bowlers);
        return playerService.createPlayers(players);
    }

    @GetMapping("/players/team/{teamId}")
    public List<Player> getAllPlayersInTeam(@PathVariable String teamId) {
        return playerService.findPlayersByTeamId(teamId);
    }

    @GetMapping("/players/team/{teamId}/batsmen")
    public List<Player> getAllBatsmenInTeam(@PathVariable String teamId) {
        return playerService.findSpecificPlayersInTeam(PlayerType.BATSMAN, teamId);
    }

    @GetMapping("/players/team/{teamId}/bowlers")
    public List<Player> getAllBowlersInTeam(@PathVariable String teamId) {
        return playerService.findSpecificPlayersInTeam(PlayerType.BOWLER, teamId);
    }

}

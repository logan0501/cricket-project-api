package com.logan.cricketProject.service.playerService;

import com.logan.cricketProject.entity.Player;
import com.logan.cricketProject.entity.Team;
import com.logan.cricketProject.enums.PlayerType;
import com.logan.cricketProject.exceptions.ResourceNotFoundException;
import com.logan.cricketProject.repository.PlayerRepository;
import com.logan.cricketProject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    PlayerRepository playerRepository;
    TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new ResourceNotFoundException("Player with Id " + playerId + " not found"));
        return player;
    }

    @Override
    public Player updatePlayer(String playerId, Player player) {
        Player exitingPlayer = findPlayerById(playerId);

        exitingPlayer.setPlayerName(player.getPlayerName());
        exitingPlayer.setPlaying(player.isPlaying());
        exitingPlayer.setPlayerType(player.getPlayerType());
        exitingPlayer.setTeamId(player.getTeamId());

        return playerRepository.save(exitingPlayer);
    }

    @Override
    public void deletePlayer(String playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public List<Player> findByPlayerType(PlayerType playerType) {
        return playerRepository.findByPlayerType(playerType);
    }

    @Override
    public List<Player> createPlayers(List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("No players provided");
        }
        String teamId = players.get(0).getTeamId();
        PlayerType currentPlayerType = players.get(0).getPlayerType();
        Team team = teamRepository.findById(teamId).orElseThrow(()->new IllegalArgumentException("Given Team Id does not exist"));
        String playerTypeStr = currentPlayerType == PlayerType.BATSMAN ? "batsmen" : "bowlers";

        long totalPlayers = playerRepository.countByTeamId(teamId);
        if (totalPlayers + players.size() > 14) {
            throw new IllegalStateException("Adding these "+ playerTypeStr+" will exceed the team limit of 14 players.");
        }

        long playerTypeCount = playerRepository.countByTeamIdAndPlayerType(teamId,currentPlayerType);
        if(playerTypeCount+players.size() > 7){
            throw new IllegalStateException("Adding these "+playerTypeStr+" will exceed the allowed 7 "+playerTypeStr+ " per team.");
        }

        List<Player> updatedPlayers =  playerRepository.saveAll(players);
        for(Player player:updatedPlayers){
            team.getPlayerList().add(player.getPlayerId());
        }
        teamRepository.save(team);
        return updatedPlayers;
    }

    @Override
    public List<Player> findPlayersByTeamId(String teamId) {
        return playerRepository.findPlayersByTeamId(teamId);
    }

    @Override
    public List<Player> findSpecificPlayersInTeam(PlayerType playerType, String teamId) {
        return playerRepository.findByPlayerTypeAndTeamId(playerType, teamId);
    }

    @Override
    public List<Player> getAutoPlaying11(String teamId) {
        List<Player> batsmen = playerRepository.findTop6ByTeamIdAndPlayerType(teamId, PlayerType.BATSMAN);
        List<Player> bowlers = playerRepository.findTop5ByTeamIdAndPlayerType(teamId, PlayerType.BOWLER);

        if (batsmen.size() < 6 || bowlers.size() < 5) {
            throw new IllegalStateException("Not enough players to create a valid team.");
        }

        List<Player> playing11 = new ArrayList<>();
        playing11.addAll(batsmen);
        playing11.addAll(bowlers);
        return playing11;
    }

    @Override
    public List<Player> findAllById(List<String> playerIds) {
        return playerRepository.findAllById(playerIds);
    }

}

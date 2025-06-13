package com.logan.cricketProject.service.playerService;

import com.logan.cricketProject.entity.Player;
import com.logan.cricketProject.enums.PlayerType;

import java.util.List;

public interface PlayerService {
    List<Player> findAllPlayers();

    Player findPlayerById(String playerId);

    Player updatePlayer(String playerId, Player player);

    void deletePlayer(String playerId);

    List<Player> findByPlayerType(PlayerType playerType);

    List<Player> createPlayers(List<Player> players);

    List<Player> findPlayersByTeamId(String teamId);

    List<Player> findSpecificPlayersInTeam(PlayerType playerType, String teamId);

    List<Player> getAutoPlaying11(String teamId);

    List<Player> findAllById(List<String> playerIds);
}

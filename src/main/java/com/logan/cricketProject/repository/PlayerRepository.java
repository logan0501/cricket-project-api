package com.logan.cricketProject.repository;

import com.logan.cricketProject.entity.Player;
import com.logan.cricketProject.enums.PlayerType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findByPlayerType(PlayerType playerType);

    List<Player> findPlayersByTeamId(String teamId);

    List<Player> findByPlayerTypeAndTeamId(PlayerType playerType, String teamId);

    long countByTeamId(String teamId);

    long countByTeamIdAndPlayerType(String teamId, PlayerType playerType);

    List<Player> findTop6ByTeamIdAndPlayerType(String teamId, PlayerType playerType);

    List<Player> findTop5ByTeamIdAndPlayerType(String teamId, PlayerType playerType);
}

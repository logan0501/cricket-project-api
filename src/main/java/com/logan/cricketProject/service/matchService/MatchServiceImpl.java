package com.logan.cricketProject.service.matchService;

import com.logan.cricketProject.entity.*;
import com.logan.cricketProject.enums.PlayerType;
import com.logan.cricketProject.enums.TossDecision;
import com.logan.cricketProject.repository.MatchRepository;
import com.logan.cricketProject.service.playerService.PlayerServiceImpl;
import com.logan.cricketProject.service.teamService.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {
    MatchRepository matchRepository;
    TeamServiceImpl teamService;
    PlayerServiceImpl playerService;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, TeamServiceImpl teamService, PlayerServiceImpl playerService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.playerService = playerService;
    }


    private Match startMatch(Match match, List<Player> team1PlayerDetails, List<Player> team2PlayerDetails) {
        String battingTeamId;
        String bowlingTeamId;
        List<Player> battingTeam11;
        List<Player> bowlingTeam11;

        Toss toss = match.getToss();
        if (toss.getTossDecision() == TossDecision.BAT) {
            battingTeamId = toss.getWonByTeamId();
            if (battingTeamId.equals(match.getTeam1Id())) {
                battingTeam11 = team1PlayerDetails;
                bowlingTeam11 = team2PlayerDetails;
                bowlingTeamId = match.getTeam2Id();
            } else {
                battingTeam11 = team2PlayerDetails;
                bowlingTeam11 = team1PlayerDetails;
                bowlingTeamId = match.getTeam1Id();
            }
        } else {
            bowlingTeamId = toss.getWonByTeamId();
            if (bowlingTeamId.equals(match.getTeam1Id())) {
                battingTeamId = match.getTeam2Id();
                battingTeam11 = team2PlayerDetails;
                bowlingTeam11 = team1PlayerDetails;
            } else {
                battingTeamId = match.getTeam1Id();
                battingTeam11 = team1PlayerDetails;
                bowlingTeam11 = team2PlayerDetails;
            }
        }

//        First batting team will be bowling team in second innings
        List<String> team1BatterIds = battingTeam11.stream().map(Player::getPlayerId).toList();
        List<String> team2BowlerIds = bowlingTeam11.stream().filter(p -> p.getPlayerType() == PlayerType.BOWLER).map(Player::getPlayerId).toList();

        List<String> team2BatterIds = bowlingTeam11.stream().map(Player::getPlayerId).toList();
        List<String> team1BowlerIds = battingTeam11.stream().filter(p -> p.getPlayerType() == PlayerType.BOWLER).map(Player::getPlayerId).toList();


        Innings.setNumberOfOvers(match.getNumberOfOvers());

        Innings innings1 = new Innings(battingTeamId, team1BatterIds, bowlingTeamId, team2BowlerIds);
        match.setInnings1(innings1);
        innings1.simulateInnings(false, -1);

        Innings innings2 = new Innings(bowlingTeamId, team2BatterIds, battingTeamId, team1BowlerIds);
        match.setInnings2(innings2);
        innings2.simulateInnings(true, innings1.getRuns());

        match.decideWinner();
        return matchRepository.insert(match);
    }

    @Override
    public Match createMatch(Match match) {
        String team1Id = match.getTeam1Id();
        String team2Id = match.getTeam2Id();

        List<String> team1Playing11 = match.getTeam1Playing11();
        List<String> team2Playing11 = match.getTeam2Playing11();

        List<Player> team1PlayerDetails;
        List<Player> team2PlayerDetails;

        if (team1Playing11 != null) {
            team1PlayerDetails = checkCustomPlaying11AndRetrievePlayers(team1Playing11, team1Id);
        } else {
            team1PlayerDetails = playerService.getAutoPlaying11(team1Id);
            team1Playing11 = getPlayerIds(team1PlayerDetails);
        }

        if (team2Playing11 != null) {
            team2PlayerDetails = checkCustomPlaying11AndRetrievePlayers(team2Playing11, team2Id);
        } else {
            team2PlayerDetails = playerService.getAutoPlaying11(team2Id);
            team2Playing11 = getPlayerIds(playerService.getAutoPlaying11(team2Id));
        }

        Toss toss = Toss.simulate(team1Id, team2Id);
        match.setTeam1Playing11(team1Playing11);
        match.setTeam2Playing11(team2Playing11);
        match.setToss(toss);

        return startMatch(match, team1PlayerDetails, team2PlayerDetails);

    }

    private List<Player> checkCustomPlaying11AndRetrievePlayers(List<String> playing11, String teamId) {
        if (playing11.size() != 11) {
            throw new IllegalArgumentException("Playing 11 must contain exactly 11 players.");
        }

        Team team = teamService.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Team with id " + teamId + " does not exist."));

        if (!new HashSet<>(team.getPlayerList()).containsAll(playing11)) {
            throw new IllegalArgumentException("Some players do not belong to the team " + teamId);
        }

        List<Player> players = playerService.findAllById(playing11);

        long batsmenCount = players.stream().filter(p -> p.getPlayerType() == PlayerType.BATSMAN).count();
        long bowlersCount = players.stream().filter(p -> p.getPlayerType() == PlayerType.BOWLER).count();

        if (batsmenCount != 6 || bowlersCount != 5) {
            throw new IllegalArgumentException("Custom playing 11 must include 6 batsmen and 5 bowlers");
        }
        return players;
    }

    private List<String> getPlayerIds(List<Player> players) {
        return players.stream().map(Player::getPlayerId).collect(Collectors.toList());
    }

}

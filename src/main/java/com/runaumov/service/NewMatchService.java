package com.runaumov.service;

import com.runaumov.GameScore;
import com.runaumov.MatchScore;
import com.runaumov.dao.PlayerDao;
import com.runaumov.dto.RequestNewMatchDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import lombok.Getter;

import java.util.*;

public class NewMatchService {

    private final PlayerDao playerDao = new PlayerDao();
    @Getter private Map<UUID, Match> beginingMatches = new HashMap<>();

    public UUID initMatch(RequestNewMatchDto requestNewMatchDto) {
        Player player1 = getPlayer(requestNewMatchDto.getPlayer1Name());
        Player player2 = getPlayer(requestNewMatchDto.getPlayer2Name());

        // TODO: рассмотреть варик создания отдельного метода
        MatchScore matchScore = MatchScore.builder()
                .scorePlayer1(GameScore.LOVE)
                .scorePlayer2(GameScore.LOVE)
                .build();

        Match match = Match.builder()
                .player1Id(player1)
                .player2Id(player2)
                .matchScore(matchScore)
                .build();

        UUID matchId = UUID.randomUUID();
        beginingMatches.put(matchId, match);
        return matchId;
    }

    public Match getMatchById(UUID matchId) {
        return beginingMatches.get(matchId);
    }

    // TODO: подумать над player.get()
    private Player getPlayer(String playerName) {
        if (!isPlayerExist(playerName)) {
            addPlayerInDatabase(playerName);
        }
        Optional<Player> player = playerDao.findByName(playerName);
        return player.get();
    }

    private void addPlayerInDatabase(String playerName) {
        Player player = Player.builder()
                .name(playerName)
                .build();
        playerDao.addPlayer(player);
    }

    private boolean isPlayerExist(String playerName) {
        Optional<Player> player = playerDao.findByName(playerName);
        return player.isPresent();
    }

}

package com.runaumov.service;


import com.runaumov.PointScore;
import com.runaumov.MatchStorage;
import com.runaumov.dao.PlayerDao;
import com.runaumov.dto.RequestNewMatchDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.MatchScore;
import com.runaumov.entity.Player;
import java.util.*;

public class NewMatchService {

    private final PlayerDao playerDao = new PlayerDao();

    public UUID initMatch(RequestNewMatchDto requestNewMatchDto) {
        Player player1 = getPlayer(requestNewMatchDto.getPlayer1Name());
        Player player2 = getPlayer(requestNewMatchDto.getPlayer2Name());

        // TODO: рассмотреть варик создания отдельного метода
        MatchScore matchScore = MatchScore.builder()
                .pointScorePlayer1(PointScore.LOVE.name())
                .pointScorePlayer2(PointScore.LOVE.name())
                .previousSets(new ArrayList<>())
                .build();

        Match match = Match.builder()
                .player1Id(player1)
                .player2Id(player2)
                .matchScore(matchScore)
                .build();

        UUID matchId = UUID.randomUUID();
        MatchStorage.getInstance().addMatch(matchId, match);
        return matchId;
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

package com.runaumov.service.managment;

import com.runaumov.exception.ModelNotFoundException;
import com.runaumov.model.GameType;
import com.runaumov.model.MatchType;
import com.runaumov.model.PointScore;
import com.runaumov.storage.MatchStorage;
import com.runaumov.dao.PlayerDao;
import com.runaumov.dto.request.RequestNewMatchDto;
import com.runaumov.entity.Match;
import com.runaumov.model.MatchScore;
import com.runaumov.entity.Player;
import java.util.*;

public class NewMatchService {

    private final PlayerDao playerDao = new PlayerDao();

    public UUID initMatch(RequestNewMatchDto requestNewMatchDto) {
        Player playerOne = getOrAddPlayer(requestNewMatchDto.getPlayer1Name());
        Player playerTwo = getOrAddPlayer(requestNewMatchDto.getPlayer2Name());

        MatchScore matchScore = createDefaultMatchScore();
        Match match = createDefaultMatch(playerOne, playerTwo, matchScore);

        UUID matchId = UUID.randomUUID();
        MatchStorage.getInstance().addMatch(matchId, match);

        return matchId;
    }

    private MatchScore createDefaultMatchScore() {
        return MatchScore.builder()
                .pointScorePlayer1(PointScore.LOVE.name())
                .pointScorePlayer2(PointScore.LOVE.name())
                .gameType(GameType.NORMAL)
                .matchType(MatchType.NORMAL)
                .build();
    }

    private Match createDefaultMatch(Player playerOne, Player playerTwo, MatchScore matchScore) {
        return Match.builder()
                .player1Id(playerOne)
                .player2Id(playerTwo)
                .matchScore(matchScore)
                .build();
    }

    private Player getOrAddPlayer(String playerName) {
        if (!isPlayerExist(playerName)) {
            addPlayerInDatabase(playerName);
        }
        Optional<Player> player = playerDao.findByName(playerName);

        if (player.isPresent()) {
            return player.get();
        } else {
            throw new ModelNotFoundException(String.format(
                    "Player '%s' cannot be returned in database", playerName));
        }
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
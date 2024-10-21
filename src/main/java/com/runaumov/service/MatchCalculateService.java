package com.runaumov.service;

import com.runaumov.MatchScore;
import com.runaumov.dto.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;

public class MatchCalculateService {

    public Match updateMatchScore(RequestMatchScoreDto requestMatchScoreDto) {
        Match currentMatch = requestMatchScoreDto.getMatch();
        Player player1 = currentMatch.getPlayer1Id();
        Player player2 = currentMatch.getPlayer2Id();
        MatchScore currentMatchScore = currentMatch.getMatchScore();
        int winnerId = requestMatchScoreDto.getPlayerId();
        Player winner = getWinner(currentMatch, winnerId);

        if (isDeuce()) {
            // логика для 40:40
        }

        if (isTiebreak()) {

        }

        if (isGameWin()) {
            // логика для выигранного гейма, гейм обнуляется, счетчик сета увеличивается на +1
        }

        if (isSetWin()) {
            // логика для выигранного сета, сет обнуляется, счетчик матча увеличивается на +1
        }

        currentMatchScore.
        // логика для подсчета очков в гейме

        return null;
    }

    private boolean isDeuce() { // 40:40
        return false;
    }

    private boolean isTiebreak() { // 6:6
        return false;
    }

    private boolean isGameWin() {
        return false;
    }

    private boolean isSetWin() {
        return false;
    }

    private Player getWinner(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        if (player1.getId() == winnerId) {
            return player1;
        } else if (player2.getId() == winnerId) {
            return player2;
        } else {
            // TODO : доделать
            throw new IllegalArgumentException("");
        }
    }
}

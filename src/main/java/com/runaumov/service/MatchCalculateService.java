package com.runaumov.service;

import com.runaumov.PointScore;
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

        if (isDeuce()) {
            // логика для 40:40
        }

        if (isTiebreak()) {

        }

        if (isGameWin(currentMatch)) {
            return updateSetScore(currentMatch, winnerId);
        }

        if (isSetWin()) {
            // логика для выигранного сета, сет обнуляется, счетчик матча увеличивается на +1
        }

        return updateGameScore(currentMatch, winnerId);

    }

    private boolean isDeuce() { // 40:40
        return false;
    }

    private boolean isTiebreak() { // 6:6
        return false;
    }

    private boolean isGameWin(Match match) {
        PointScore pointScore1 = match.getMatchScore().getPointScorePlayer1();
        PointScore pointScore2 = match.getMatchScore().getPointScorePlayer2();

        return (pointScore1.equals(PointScore.FORTY) || pointScore2.equals(PointScore.FORTY));
    }

    private boolean isSetWin() {
        return false;
    }

    // TODO : исключить дублирование
    private Match updateGameScore(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        if (player1.getId() == winnerId) {
            PointScore pointScorePlayer1 = match.getMatchScore().getPointScorePlayer1();
            PointScore updatedPointScore = PointScore.getNextGameScore(pointScorePlayer1);
            match.getMatchScore().setPointScorePlayer1(updatedPointScore);
            return match;
        } else if (player2.getId() == winnerId) {
            PointScore pointScorePlayer2 = match.getMatchScore().getPointScorePlayer2();
            PointScore updatedPointScore = PointScore.getNextGameScore(pointScorePlayer2);
            match.getMatchScore().setPointScorePlayer2(updatedPointScore);
            return match;
        } else {
            // TODO : доделать
            throw new IllegalArgumentException("Ошибка");
        }
    }


    // TODO : исключить дублирование
    private Match updateSetScore(Match match, int winnerId) {
        Player player1 = match.getPlayer1Id();
        Player player2 = match.getPlayer2Id();
        if (player1.getId() == winnerId) {
            match.getMatchScore().setGameScorePlayer1(match.getMatchScore().getGameScorePlayer1() + 1);
            match.getMatchScore().setPointScorePlayer1(PointScore.LOVE);
            match.getMatchScore().setPointScorePlayer2(PointScore.LOVE);
            return match;
        } else if (player2.getId() == winnerId) {
            match.getMatchScore().setGameScorePlayer2(match.getMatchScore().getGameScorePlayer2() + 1);
            match.getMatchScore().setPointScorePlayer1(PointScore.LOVE);
            match.getMatchScore().setPointScorePlayer2(PointScore.LOVE);
            return match;
        } else {
            // TODO : доделать
            throw new IllegalArgumentException("Ошибка");
        }
    }
}

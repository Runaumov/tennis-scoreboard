package com.runaumov.service;

import com.runaumov.PointScore;
import com.runaumov.entity.MatchScore;
import com.runaumov.dto.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;

public class MatchCalculateService {

    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;
    private static final int MIN_WIN_GAMES = 6;
    private static final int TIEBREAK_START_SCORE = 6;

    public Match updateMatchScore(RequestMatchScoreDto requestMatchScoreDto) {
        Match currentMatch = requestMatchScoreDto.getMatch();
        MatchScore currentMatchScore = currentMatch.getMatchScore();
        int winnerId = requestMatchScoreDto.getPlayerId();

        if (isDeuce()) {
            // логика для 40:40
        }

        if (isTiebreak(currentMatchScore)) {

        }

        if (isGameWin(currentMatch)) {
            return updateSetScore(currentMatch, winnerId);
        }

        if (isSetWin(currentMatch)) {
            int gameScorePlayer1 = currentMatchScore.getGameScorePlayer1();
            int gameScorePlayer2 = currentMatchScore.getGameScorePlayer2();

            currentMatchScore.addPreviousSet(gameScorePlayer1, gameScorePlayer2);
            currentMatchScore.setDefaultGameScore();

            return currentMatch;
        }

        return updateGameScore(currentMatch, winnerId);
    }

    private boolean isDeuce() { // 40:40
        return false;
    }

    private boolean isTiebreak(MatchScore matchScore) { // 6:6
        int gameScorePlayer1 = matchScore.getGameScorePlayer1();
        int gameScorePlayer2 = matchScore.getGameScorePlayer2();

        return gameScorePlayer1 == TIEBREAK_START_SCORE && gameScorePlayer2 == TIEBREAK_START_SCORE;
    }

    private boolean isGameWin(Match match) {
        PointScore pointScore1 = match.getMatchScore().getPointScorePlayer1();
        PointScore pointScore2 = match.getMatchScore().getPointScorePlayer2();

        return (pointScore1.equals(PointScore.FORTY) || pointScore2.equals(PointScore.FORTY));
    }

    private boolean isSetWin(Match match) {
        int gameScorePlayer1 = match.getMatchScore().getGameScorePlayer1();
        int gameScorePlayer2 = match.getMatchScore().getGameScorePlayer2();
        int gameDifference = Math.abs(gameScorePlayer1 - gameScorePlayer2);

        if (gameDifference >= GAME_ADVANTAGE_DIFFERENCE &&
           (gameScorePlayer1 >= MIN_WIN_GAMES || gameScorePlayer2 >= MIN_WIN_GAMES)
        ) {
            return true;
        }
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

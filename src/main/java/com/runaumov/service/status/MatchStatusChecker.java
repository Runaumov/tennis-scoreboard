package com.runaumov.service.status;

import com.runaumov.model.PointScore;
import com.runaumov.entity.Match;

public class MatchStatusChecker {

    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;
    private static final int MIN_WIN_GAMES = 6;
    private static final int WINNING_SET_COUNT = 2;

    // TODO : реализовать
    public boolean isDeuce() { // 40:40
        return false;
    }

    public boolean isGameWin(Match match) {
        String pointScore1 = match.getMatchScore().getPointScorePlayer1();
        String pointScore2 = match.getMatchScore().getPointScorePlayer2();

        return (pointScore1.toUpperCase().equals(PointScore.WIN.name()) ||
                pointScore2.toUpperCase().equals(PointScore.WIN.name()));
    }

    // TODO : Возможно тут баг в условии. Надо чекнуть условия завершения сета
    public boolean isSetWin(Match match) {
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

    public boolean isMatchWin(Match match) {
        int setScoreForPlayer1 = match.getMatchScore().getSetScorePlayer1();
        int setScoreForPlayer2 = match.getMatchScore().getSetScorePlayer2();
        return setScoreForPlayer1 == WINNING_SET_COUNT || setScoreForPlayer2 == WINNING_SET_COUNT;
    }
}

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
        String pointScoreForPlayerOne = match.getMatchScore().getPointScorePlayer1();
        String pointScoreForPlayerTwo = match.getMatchScore().getPointScorePlayer2();

        return (pointScoreForPlayerOne.toUpperCase().equals(PointScore.WIN.name()) ||
                pointScoreForPlayerTwo.toUpperCase().equals(PointScore.WIN.name()));
    }

    // TODO : Возможно тут баг в условии. Надо чекнуть условия завершения сета
    public boolean isSetWin(Match match) {
        int gameScorePlayerOne = match.getMatchScore().getGameScorePlayer1();
        int gameScorePlayerTwo = match.getMatchScore().getGameScorePlayer2();
        int gameScoreDifference = Math.abs(gameScorePlayerOne - gameScorePlayerTwo);

        return  gameScoreDifference >= GAME_ADVANTAGE_DIFFERENCE &&
                (gameScorePlayerOne >= MIN_WIN_GAMES || gameScorePlayerTwo >= MIN_WIN_GAMES);
    }

    public boolean isMatchWin(Match match) {
        int setScoreForPlayer1 = match.getMatchScore().getSetScorePlayer1();
        int setScoreForPlayer2 = match.getMatchScore().getSetScorePlayer2();

        return setScoreForPlayer1 == WINNING_SET_COUNT || setScoreForPlayer2 == WINNING_SET_COUNT;
    }
}

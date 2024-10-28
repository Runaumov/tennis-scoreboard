package com.runaumov.service;

import com.runaumov.PointScore;
import com.runaumov.entity.Match;
import com.runaumov.entity.MatchScore;

import java.util.List;

public class MatchStatusChecker {


    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;
    private static final int MIN_WIN_GAMES = 6;

    // TODO : реализовать
    public boolean isDeuce() { // 40:40
        return false;
    }

    public boolean isGameWin(Match match) {
        String pointScore1 = match.getMatchScore().getPointScorePlayer1();
        String pointScore2 = match.getMatchScore().getPointScorePlayer2();

        return (pointScore1.toUpperCase().equals(PointScore.FORTY.name()) || pointScore2.toUpperCase().equals(PointScore.FORTY.name()));
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

    public boolean isMatchCompleted(Match match) {
        MatchScore matchScore = match.getMatchScore();
        List<int[]> previousSets = matchScore.getPreviousSets();
        int size = previousSets.size();
        return size == 4 && isSetWin(match);
    }
}

package com.runaumov.service;

import com.runaumov.MatchType;
import com.runaumov.entity.MatchScore;

public class TieBreakService {

    private static final int TIEBREAK_START_SCORE = 6;
    private static final int MIN_WIN_GAMES_FOR_TIEBREAK = 6;
    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;

    public void checkForTiebreak(MatchScore matchScore) { // 6:6
        int gameScorePlayer1 = matchScore.getGameScorePlayer1();
        int gameScorePlayer2 = matchScore.getGameScorePlayer2();

        if (gameScorePlayer1 == TIEBREAK_START_SCORE && gameScorePlayer2 == TIEBREAK_START_SCORE) {
            if (matchScore.getMatchType().equals(MatchType.NORMAL)) {
                matchScore.setDefaultPointScore();
                matchScore.setMatchType(MatchType.TIEBREAK);
            }
        }
    }

    public boolean isTiebreakWon(MatchScore matchScore) {
        if (matchScore.getMatchType().equals(MatchType.TIEBREAK)) {
            int pointScorePlayer1 = Integer.parseInt(matchScore.getPointScorePlayer1());
            int pointScorePlayer2 = Integer.parseInt(matchScore.getPointScorePlayer2());
            int gameDifference = Math.abs(pointScorePlayer1 - pointScorePlayer2);

            return gameDifference >= GAME_ADVANTAGE_DIFFERENCE &&
                    (pointScorePlayer1 >= MIN_WIN_GAMES_FOR_TIEBREAK || pointScorePlayer2 >= MIN_WIN_GAMES_FOR_TIEBREAK);
        }
        return false;
    }
}

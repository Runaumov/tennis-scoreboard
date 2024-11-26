package com.runaumov.service.tiebreak;

import com.runaumov.exception.ModelNotFoundException;
import com.runaumov.model.MatchType;
import com.runaumov.model.MatchScore;

public class TiebreakService {

    private static final int TIEBREAK_START_SCORE = 6;
    private static final int MIN_WIN_GAMES_FOR_TIEBREAK = 6;
    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;

    public boolean checkForStartTiebreak(MatchScore matchScore) {
        if (matchScore == null) {
            throw new ModelNotFoundException("Matchscore cannot be null");
        }

        return matchScore.getGameScorePlayer1() == TIEBREAK_START_SCORE &&
                matchScore.getGameScorePlayer2() == TIEBREAK_START_SCORE;
    }

    public boolean getTiebreakStatus(MatchScore matchScore) {
        return matchScore.getMatchType().equals(MatchType.TIEBREAK);
    }

    public void startTiebreak(MatchScore matchScore) {
        matchScore.setDefaultPointScore();
        matchScore.setMatchType(MatchType.TIEBREAK);
    }

    public boolean isTiebreakWon(MatchScore matchScore) {
        if (matchScore.getMatchType().equals(MatchType.TIEBREAK)) {
            return false;
        }

        int pointScorePlayerOne = Integer.parseInt(matchScore.getPointScorePlayer1());
        int pointScorePlayerTwo = Integer.parseInt(matchScore.getPointScorePlayer2());
        int gameDifference = Math.abs(pointScorePlayerOne - pointScorePlayerTwo);

        return gameDifference >= GAME_ADVANTAGE_DIFFERENCE &&
                (pointScorePlayerOne >= MIN_WIN_GAMES_FOR_TIEBREAK ||
                        pointScorePlayerTwo >= MIN_WIN_GAMES_FOR_TIEBREAK);
    }
}

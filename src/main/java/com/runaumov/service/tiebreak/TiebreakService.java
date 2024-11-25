package com.runaumov.service.tiebreak;

import com.runaumov.exception.ModelNotFoundException;
import com.runaumov.model.MatchType;
import com.runaumov.model.MatchScore;

public class TieBreakService {

    private static final int TIEBREAK_START_SCORE = 6;
    private static final int MIN_WIN_GAMES_FOR_TIEBREAK = 6;
    private static final int GAME_ADVANTAGE_DIFFERENCE = 2;

    public void checkForTiebreak(MatchScore matchScore) {
        if (matchScore == null) {
            throw new ModelNotFoundException("Matchscore cannot be null");
        }

        if (!matchScore.getMatchType().equals(MatchType.NORMAL)) {
            return;
        }

        if (isEligibleForTiebreak(matchScore)) {
            startTiebreak(matchScore);
        }
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

    private boolean isEligibleForTiebreak(MatchScore matchScore) {
        return matchScore.getGameScorePlayer1() == TIEBREAK_START_SCORE &&
                matchScore.getGameScorePlayer2() == TIEBREAK_START_SCORE;
    }

    private void startTiebreak(MatchScore matchScore) {
        matchScore.setDefaultPointScore();
        matchScore.setMatchType(MatchType.TIEBREAK);
    }
}

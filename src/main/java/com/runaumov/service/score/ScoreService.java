package com.runaumov.service.score;

import com.runaumov.entity.Player;
import com.runaumov.exception.ScoreUpdateException;
import com.runaumov.model.MatchScore;
import com.runaumov.model.MatchType;
import com.runaumov.model.PointScore;
import com.runaumov.entity.Match;

public class ScoreService {
    private static final int SET_POINT_TO_WINNER = 7;
    private static final int SET_POINT_TO_LOOSER = 6;

    public Match updatePointScore(Match match, int winnerId) {
        if (match.getMatchScore().getMatchType().equals(MatchType.TIEBREAK)) {
            updateTiebreakScore(match, winnerId);
        } else {
            updateNormalScore(match, winnerId);
        }

        return match;
    }

    public Match updateGameScore(Match match, int winnerId) {
        MatchScore matchScore = match.getMatchScore();
        int playerOneId = match.getPlayer1Id().getId();
        int playerTwoId = match.getPlayer2Id().getId();

        if (playerOneId == winnerId) {
            matchScore.setGameScorePlayer1(match.getMatchScore().getGameScorePlayer1() + 1);
            matchScore.setDefaultPointScore();
            return match;
        } else if (playerTwoId == winnerId) {
            matchScore.setGameScorePlayer2(match.getMatchScore().getGameScorePlayer2() + 1);
            matchScore.setDefaultPointScore();
            return match;
        } else {
            throw new ScoreUpdateException(
                    String.format("Player ID '%s' does not match any player in the match.", winnerId));
        }
    }

    public Match updateSetScore(Match match, int winnerId) {
        MatchScore matchScore = match.getMatchScore();
        int playerOneId = match.getPlayer1Id().getId();
        int playerTwoId = match.getPlayer2Id().getId();

        if (playerOneId == winnerId) {
            matchScore.setSetScorePlayer1(match.getMatchScore().getSetScorePlayer1() + 1);
            matchScore.setDefaultPointScore();
            return match;
        } else if (playerTwoId == winnerId) {
            matchScore.setSetScorePlayer2(match.getMatchScore().getSetScorePlayer2() + 1);
            matchScore.setDefaultPointScore();
            return match;
        } else {
            throw new ScoreUpdateException(
                    String.format("Player ID '%s' does not match any player in the match.", winnerId));
        }
    }

    public Match updateSetScoreForTiebreak(Match match, int winnerId) {
        MatchScore matchScore = match.getMatchScore();
        updateSetScore(match, winnerId);

        matchScore.setDefaultPointScore();
        matchScore.setDefaultGameScore();
        matchScore.setMatchType(MatchType.NORMAL);

        return match;
    }

    private void updateNormalScore(Match match, int winnerId) {
        MatchScore matchScore = match.getMatchScore();
        int playerOneId = match.getPlayer1Id().getId();
        int playerTwoId = match.getPlayer2Id().getId();
        String pointScorePlayerOne = match.getMatchScore().getPointScorePlayer1();
        String pointScorePlayerTwo = match.getMatchScore().getPointScorePlayer2();

        if (playerOneId == winnerId) {
            String updatedPointScore = PointScore.getNextGameScore(PointScore.getPointScoreFromString(pointScorePlayerOne));
            matchScore.setPointScorePlayer1(updatedPointScore);
        } else if (playerTwoId == winnerId) {
            String updatedPointScore = PointScore.getNextGameScore(PointScore.getPointScoreFromString(pointScorePlayerTwo));
            matchScore.setPointScorePlayer2(updatedPointScore);
        } else {
            throw new ScoreUpdateException(
                    String.format("Player ID '%s' does not match any player in the match.", winnerId));
        }
    }

    private void updateTiebreakScore(Match match, int winnerId) {
        MatchScore matchScore = match.getMatchScore();
        int playerOneId = match.getPlayer1Id().getId();
        int playerTwoId = match.getPlayer2Id().getId();
        String pointScorePlayerOne = match.getMatchScore().getPointScorePlayer1();
        String pointScorePlayerTwo = match.getMatchScore().getPointScorePlayer2();

        if (playerOneId == winnerId) {
            matchScore.setPointScorePlayer1(incrementTiebreakScore(pointScorePlayerOne));
        } else if (playerTwoId == winnerId) {
            matchScore.setPointScorePlayer2(incrementTiebreakScore(pointScorePlayerTwo));
        } else {
            throw new ScoreUpdateException(
                    String.format("Player ID '%s' does not match any player in the match.", winnerId));
        }
    }

    private String incrementTiebreakScore(String currentScore) {
        int score = Integer.parseInt(currentScore);
        return String.valueOf(score + 1);
    }

}
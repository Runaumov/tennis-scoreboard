package com.runaumov.service.status;

import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import com.runaumov.exception.ScoreUpdateException;
import com.runaumov.model.MatchScore;

public class MatchResultService {
    private static final int WINNING_SET_COUNT = 2;

    public Player getWinner(Match match) {
        MatchScore matchScore = match.getMatchScore();
        Player playerOne = match.getPlayer1Id();
        Player playerTwo = match.getPlayer2Id();

        if (matchScore.getSetScorePlayer1() == WINNING_SET_COUNT) {
            return determinePlayer(match, playerOne);
        } else if (matchScore.getSetScorePlayer2() == WINNING_SET_COUNT) {
            return determinePlayer(match, playerTwo);
        } else {
            throw new ScoreUpdateException("Error for get winner");
        }
    }

    private Player determinePlayer(Match match, Player winner) {
        setWinner(match, winner);
        return winner;
    }

    private void setWinner(Match match, Player winner) {
        match.setWinner(winner);
    }
}

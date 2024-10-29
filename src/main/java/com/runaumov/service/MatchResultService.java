package com.runaumov.service;

import com.runaumov.entity.Match;
import com.runaumov.entity.Player;

public class MatchResultService {
    private static final int WINNING_SET_COUNT = 2;

    public Player getWinner(Match match) {
        Player player1Id = match.getPlayer1Id();
        Player player2Id = match.getPlayer2Id();

        if (match.getMatchScore().getSetScorePlayer1() == WINNING_SET_COUNT) {
            return player1Id;
        } else if (match.getMatchScore().getSetScorePlayer2() == WINNING_SET_COUNT) {
            return player2Id;
        } else {
        // TODO : доделать
        throw new IllegalArgumentException("Ошибка");
        }
    }
}
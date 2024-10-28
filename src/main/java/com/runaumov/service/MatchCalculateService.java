package com.runaumov.service;

import com.runaumov.MatchType;
import com.runaumov.entity.MatchScore;
import com.runaumov.dto.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class MatchCalculateService {

    private static final int SET_POINT_TO_WINNER = 7;
    private static final int SET_POINT_TO_LOOSER = 6;

    private ScoreService scoreService;
    private TieBreakService tieBreakService;
    private MatchStatusChecker checker;

    public Match updateMatchScore(RequestMatchScoreDto requestMatchScoreDto) {
        Match currentMatch = requestMatchScoreDto.getMatch();
        MatchScore currentMatchScore = currentMatch.getMatchScore();
        int winnerId = requestMatchScoreDto.getPlayerId();

        // TODO : реализовать
        if (checker.isDeuce()) {
            // логика для 40:40
        }

        if (checker.isGameWin(currentMatch)) {
            return scoreService.updateSetScore(currentMatch, winnerId);
        }

        if (checker.isSetWin(currentMatch)) {
            int gameScorePlayer1 = currentMatchScore.getGameScorePlayer1();
            int gameScorePlayer2 = currentMatchScore.getGameScorePlayer2();

            currentMatchScore.addPreviousSet(gameScorePlayer1, gameScorePlayer2);
            currentMatchScore.setDefaultGameScore();

            return currentMatch;
        }

        if (tieBreakService.isTiebreakWon(currentMatchScore)) {
            Player player1 = currentMatch.getPlayer1Id();
            Player player2 = currentMatch.getPlayer2Id();

            if (player1.getId() == winnerId) {
                currentMatchScore.addPreviousSet(SET_POINT_TO_WINNER, SET_POINT_TO_LOOSER);
            } else if (player2.getId() == winnerId){
                currentMatchScore.addPreviousSet(SET_POINT_TO_LOOSER, SET_POINT_TO_WINNER);
            }
            currentMatchScore.setDefaultPointScore();
            currentMatchScore.setDefaultGameScore();
            currentMatchScore.setMatchType(MatchType.NORMAL);
        }

        tieBreakService.checkForTiebreak(currentMatchScore);
        return scoreService.updateGameScore(currentMatch, winnerId);
    }

}
package com.runaumov.service.score;

import com.runaumov.model.MatchType;
import com.runaumov.model.MatchScore;
import com.runaumov.dto.request.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.entity.Player;
import com.runaumov.service.status.MatchStatusChecker;
import com.runaumov.service.tiebreak.TieBreakService;
import lombok.AllArgsConstructor;

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
            return scoreService.updateGameScore(currentMatch, winnerId);
        }

        if (checker.isSetWin(currentMatch)) {
            currentMatchScore.setDefaultGameScore();
            return scoreService.updateSetScore(currentMatch, winnerId);
        }

        if (tieBreakService.isTiebreakWon(currentMatchScore)) {
            Player player1 = currentMatch.getPlayer1Id();
            Player player2 = currentMatch.getPlayer2Id();

            // TODO : реализовать
//            if (player1.getId() == winnerId) {
//                currentMatchScore.addPreviousSet(SET_POINT_TO_WINNER, SET_POINT_TO_LOOSER);
//            } else if (player2.getId() == winnerId){
//                currentMatchScore.addPreviousSet(SET_POINT_TO_LOOSER, SET_POINT_TO_WINNER);
//            }
            currentMatchScore.setDefaultPointScore();
            currentMatchScore.setDefaultGameScore();
            currentMatchScore.setMatchType(MatchType.NORMAL);
        }

        tieBreakService.checkForTiebreak(currentMatchScore);
        return scoreService.updatePointScore(currentMatch, winnerId);
    }

}
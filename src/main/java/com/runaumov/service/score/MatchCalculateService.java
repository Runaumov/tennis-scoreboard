package com.runaumov.service.score;

import com.runaumov.model.MatchScore;
import com.runaumov.dto.request.RequestMatchScoreDto;
import com.runaumov.entity.Match;
import com.runaumov.service.status.MatchStatusChecker;
import com.runaumov.service.tiebreak.TiebreakService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchCalculateService {

    private ScoreService scoreService;
    private TiebreakService tieBreakService;
    private MatchStatusChecker checker;

    public Match updateMatchScore(RequestMatchScoreDto requestMatchScoreDto) {
        Match currentMatch = requestMatchScoreDto.getMatch();
        int winnerId = requestMatchScoreDto.getPlayerId();

        MatchScore currentMatchScore = currentMatch.getMatchScore();
        Match updatedMatch = scoreService.updatePointScore(currentMatch, winnerId);

        // TODO : реализовать
        if (checker.isDeuce()) {
            // логика для 40:40
        }

        if (checker.isGameWin(currentMatch)) {
            return scoreService.updateGameScore(currentMatch, winnerId);
        }

        if (!tieBreakService.getTiebreakStatus(currentMatchScore) && tieBreakService.checkForStartTiebreak(currentMatchScore)) {
            tieBreakService.startTiebreak(currentMatchScore);
            scoreService.updatePointScore(currentMatch, winnerId);
        }

        if (tieBreakService.getTiebreakStatus(currentMatchScore) && tieBreakService.isTiebreakWon(currentMatchScore)) {
            scoreService.updateSetScoreForTiebreak(currentMatch, winnerId);
        }

        if (checker.isSetWin(currentMatch)) {
            currentMatchScore.setDefaultGameScore();
            return scoreService.updateSetScore(currentMatch, winnerId);
        }

        return updatedMatch;
    }

}
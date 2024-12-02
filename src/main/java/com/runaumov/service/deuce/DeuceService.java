package com.runaumov.service.deuce;

import com.runaumov.entity.Match;
import com.runaumov.model.GameType;
import com.runaumov.model.MatchScore;
import com.runaumov.model.MatchType;

public class DeuceService {

    public void startDeuce(MatchScore matchScore) {
        matchScore.setGameType(GameType.DEUCE);
    }

    public boolean isDeuceGameType(MatchScore matchScore) {
        return matchScore.getGameType().equals(GameType.DEUCE);
    }

}

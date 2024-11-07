package com.runaumov.storage;

import com.runaumov.entity.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchStorage {

    private static MatchStorage instance;
    private Map<UUID, Match> matches = new ConcurrentHashMap<>();

    private MatchStorage() {
    }

    public static synchronized MatchStorage getInstance() {
        if (instance == null) {
            instance = new MatchStorage();
        }
        return instance;
    }

    public void addMatch(UUID matchId, Match match) {
        matches.put(matchId, match);
    }

    public Match getMatchById(UUID matchId) {
        return matches.get(matchId);
    }

    public void matchRemoveById(UUID matchId) {
        matches.remove(matchId);
    }
}

package com.runaumov.service.managment;

import com.runaumov.dao.MatchDao;
import com.runaumov.entity.Match;
import com.runaumov.exception.DatabaseAccessException;
import com.runaumov.exception.ModelAlreadyExistException;

public class MatchRecordService {
    private final MatchDao matchDao = new MatchDao();

    public void addMatch (Match match) {
        if (matchDao.existsById(match.getId())) {
            throw new ModelAlreadyExistException(String.format("Match with ID '%s' already exists.", match.getId()));
        }

        try {
            matchDao.addMatch(match);
        } catch (Exception e) {
            throw new DatabaseAccessException("Failed to add match to the database.");
        }
    }
}

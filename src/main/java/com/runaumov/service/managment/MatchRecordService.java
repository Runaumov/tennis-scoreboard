package com.runaumov.service.managment;

import com.runaumov.dao.MatchesDao;
import com.runaumov.entity.Match;

public class MatchRecordService {

    private final MatchesDao matchesDao = new MatchesDao();

    // TODO : добавить проверку на существование матча в БД
    public void addMatch (Match match) {
        matchesDao.addMatch(match);
    }
}

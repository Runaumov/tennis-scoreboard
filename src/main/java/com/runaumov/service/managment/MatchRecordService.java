package com.runaumov.service.managment;

import com.runaumov.dao.MatchDao;
import com.runaumov.entity.Match;

public class MatchRecordService {

    private final MatchDao matchDao = new MatchDao();

    // TODO : добавить проверку на существование матча в БД
    public void addMatch (Match match) {
        matchDao.addMatch(match);
    }
}

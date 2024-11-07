package com.runaumov.service.managment;

import com.runaumov.dao.MatchDao;
import com.runaumov.dto.request.RequestMatchesDto;
import com.runaumov.entity.Match;
import java.util.List;

public class MatchesService {
    MatchDao matchDao = new MatchDao();

    // TODO: подумать над названием метода
    public List<Match> getPlayers(RequestMatchesDto requestMatchesDto, int pageNum, int pageSize) {

        List<Match> players;
        int offset = (pageNum - 1) * pageSize;
        String name = requestMatchesDto.getName();

        // TODO : второе условие - костыль
        if (name != null && !name.equals("")) {
            players = matchDao.findMatchByPlayerName(name, offset, pageSize);
        } else {
            players = matchDao.findAllWithPagination(offset, pageSize);
        }

        return players;
    }

    public int getTotalPages(int pageSize, String playerName) {
        long totalCount;

        if (playerName != null) {
            totalCount = matchDao.countByName(playerName);
        } else {
            totalCount = matchDao.countAll();
        }

        return (int) Math.ceil((double) totalCount / pageSize);
    }

}

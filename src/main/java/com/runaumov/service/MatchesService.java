package com.runaumov.service;

import com.runaumov.dao.MatchesDao;
import com.runaumov.dto.RequestMatchesDto;
import com.runaumov.entity.Match;

import java.util.List;
import java.util.Optional;

public class MatchesService {
    MatchesDao matchesDao = new MatchesDao();

    // TODO: подумать над названием метода
    public List<Match> getPlayers(RequestMatchesDto requestMatchesDto, int pageNum, int pageSize) {

        List<Match> players;
        int offset = (pageNum - 1) * pageSize;
        String name = requestMatchesDto.getName();

        if (name != null) {
            players = matchesDao.findByName(name, offset, pageSize);
        } else {
            players = matchesDao.findAllWithPagination(offset, pageSize);
        }

        return players;
    }

    public int getTotalPages(int pageSize, String playerName) {
        long totalCount;

        if (playerName != null) {
            totalCount = matchesDao.countByName(playerName);
        } else {
            totalCount = matchesDao.countAll();
        }

        return (int) Math.ceil((double) totalCount / pageSize);
    }
}

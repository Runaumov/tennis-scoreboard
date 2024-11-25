package com.runaumov.service.managment;

import com.runaumov.dao.MatchDao;
import com.runaumov.dto.request.RequestMatchesDto;
import com.runaumov.entity.Match;
import java.util.List;

public class MatchSearchService {
    MatchDao matchDao = new MatchDao();


    public List<Match> getMatches(RequestMatchesDto requestMatchesDto, int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        String playerName = requestMatchesDto.getName();

        return (playerName != null && !playerName.isBlank())
                ? matchDao.findMatchByPlayerName(playerName, offset, pageSize)
                : matchDao.findAllWithPagination(offset, pageSize);
    }

    public int getTotalPages(int pageSize, String playerName) {
        long totalCount = (playerName != null && !playerName.isBlank())
                ? matchDao.countByName(playerName)
                : matchDao.countAll();

        return calculateTotalPages(totalCount, pageSize);
    }

    private int calculateTotalPages(long totalCount, int pageSize) {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

}

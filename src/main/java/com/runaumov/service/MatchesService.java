package com.runaumov.service;

import com.runaumov.dao.MatchesDao;
import com.runaumov.dto.RequestMatchesDto;
import com.runaumov.entity.Match;

import java.util.List;
import java.util.Optional;

public class MatchesService {
    MatchesDao matchesDao = new MatchesDao();

    // TODO: подумать над названием метода
    public List<Match> getPlayers(RequestMatchesDto requestMatchesDto) {

        List<Match> players;
        String name = requestMatchesDto.getName();

        if (name != null) {
            players = matchesDao.findByName(name);
        } else {
            players = matchesDao.findAll();
        }

        return players;
    }

}

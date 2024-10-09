package com.runaumov.dao;

import com.runaumov.entity.Player;

import java.util.List;

public class PlayerDao implements Dao<Player> {

    @Override
    public List<Player> findAll() {
        return List.of();
    }
}

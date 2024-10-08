package com.runaumov.dao;

import java.util.List;

public interface Dao<E> {

    List<E> findAll();

    List<E> findAllWithPagination(int offset, int pageSize);
}

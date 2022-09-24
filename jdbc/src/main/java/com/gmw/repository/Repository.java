package com.gmw.repository;

import com.gmw.exceptions.SqlRepositoryException;

import java.util.List;

public interface Repository<T> {

    Long create(T t) throws SqlRepositoryException;
    void update (T t);
    void delete(Long id);
    List<T> find(String query);
}

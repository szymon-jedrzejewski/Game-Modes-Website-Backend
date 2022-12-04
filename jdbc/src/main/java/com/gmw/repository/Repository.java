package com.gmw.repository;

import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.persistence.QuerySpec;

import java.util.List;

public interface Repository<T> {

    Long create(T t) throws SqlRepositoryException;
    void update (T t) throws SqlRepositoryException;
    void delete(Long id) throws SqlRepositoryException;
    List<T> find(QuerySpec querySpec) throws SqlRepositoryException;
}

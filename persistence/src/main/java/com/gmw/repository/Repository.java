package com.gmw.repository;

import java.util.List;

public interface Repository<T> {

    void create(T t);
    void update (T t);
    void delete(Long id);
    List<T> find(String query);
}

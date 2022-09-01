package com.gmw;

import java.util.List;

public interface PersistenceManager {
    Object create(String query);
    Long update(String query);
    void delete(String query);
    List<Object> find(String query);
}

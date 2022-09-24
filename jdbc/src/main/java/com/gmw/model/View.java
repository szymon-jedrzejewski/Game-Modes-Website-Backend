package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class View extends Table implements Persistable {

    private static final String TABLE = "views";
    private Long id;
    private Long gameId;

    public View(String tableName, Long id, Long gameId) {
        super(tableName);
        this.id = id;
        this.gameId = gameId;
    }

    public View(String tableName, Long gameId) {
        super(tableName);
        this.gameId = gameId;
    }
}

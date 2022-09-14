package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class View extends Table implements Persistable {

    private static final String TABLE = "views";
    @Setter(AccessLevel.NONE)
    private Long id;
    private Long gameId;
    private List<Field> fields;

    public View(String tableName, Long id, Long gameId, List<Field> fields) {
        super(tableName);
        this.id = id;
        this.gameId = gameId;
        this.fields = fields;
    }

    public View(String tableName, Long gameId, List<Field> fields) {
        super(tableName);
        this.gameId = gameId;
        this.fields = fields;
    }
}

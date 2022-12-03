package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class View extends Table implements Persistable {

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

    public View() {
        super(null);
    }
}

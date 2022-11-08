package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game extends Table implements Persistable {
    private Long id;
    private String name;
    private String description;
    private byte[] avatar;

    public Game(String tableName) {
        super(tableName);
    }
}

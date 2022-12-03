package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Game extends Table implements Persistable {
    private Long id;
    private String name;
    private String description;
    private byte[] avatar;

    public Game(String tableName) {
        super(tableName);
    }

    public Game() {
        super(null);
    }
}

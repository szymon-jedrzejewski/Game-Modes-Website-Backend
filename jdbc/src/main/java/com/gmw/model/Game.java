package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
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

    public Game(String tableName, Long id, String name, String description, byte[] avatar) {
        super(tableName);
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
    }
}

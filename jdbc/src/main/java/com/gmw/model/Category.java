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
public class Category extends Table implements Persistable {

    private Long id;
    private String name;

    public Category() {
        super(null);
    }

    public Category(String tableName, Long id, String name) {
        super(tableName);
        this.id = id;
        this.name = name;
    }
}

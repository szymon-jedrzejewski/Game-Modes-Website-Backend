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
public class Field extends Table implements Persistable {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String label;
    private Long viewId;

    public Field(String tableName,
                 Long id,
                 String name,
                 String description,
                 String type,
                 String label,
                 Long viewId) {
        super(tableName);
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.label = label;
        this.viewId = viewId;
    }
    public Field(String tableName) {
        super(tableName);
    }

    public Field() {
        super(null);
    }
}

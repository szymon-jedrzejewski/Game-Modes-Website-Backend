package com.gmw.model;

import com.gmw.persistence.Persistable;
import com.gmw.view.enums.FieldTypeEnum;
import lombok.*;

@Getter
@Setter
public class Field extends Table implements Persistable {
    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum type;
    private String values;
    private Long viewId;

    public Field(String tableName,
                 Long id,
                 String name,
                 String description,
                 FieldTypeEnum type,
                 String values,
                 Long viewId) {
        super(tableName);
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.values = values;
        this.viewId = viewId;
    }
    public Field(String tableName) {
        super(tableName);
    }
}

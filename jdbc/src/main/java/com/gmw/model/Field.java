package com.gmw.model;

import com.gmw.persistence.Persistable;
import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.*;

@Getter
@Setter
public class Field extends Table implements Persistable {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private String values;
    private View view;

    public Field(String tableName, Long id, String name, String description, FieldTypeEnum fieldType, String values, View view) {
        super(tableName);
        this.id = id;
        this.name = name;
        this.description = description;
        this.fieldType = fieldType;
        this.values = values;
        this.view = view;
    }
    public Field(String tableName) {
        super(tableName);
    }
}

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
public class FieldValue extends Table implements Persistable {
    private Long id;
    private Long fieldId;
    private Long modId;
    private String value;

    public FieldValue() {
        super("fields_values");
    }
}

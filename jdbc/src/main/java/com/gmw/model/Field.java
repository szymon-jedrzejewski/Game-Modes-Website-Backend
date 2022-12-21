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
    private String description;
    private String type;
    private String label;
    private Long viewId;

    public Field() {
        super("fields");
    }
}

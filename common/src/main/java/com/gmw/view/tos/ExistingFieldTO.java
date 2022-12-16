package com.gmw.view.tos;

import com.gmw.view.enums.FieldTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExistingFieldTO extends NewFieldTO {
    private final Long id;

    @Builder
    public ExistingFieldTO(String name, String description, FieldTypeEnum fieldType, String label, Long id) {
        super(name, description, fieldType, label);
        this.id = id;
    }
}


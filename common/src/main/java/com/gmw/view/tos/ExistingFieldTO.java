package com.gmw.view.tos;

import com.gmw.view.enums.FieldTypeEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ExistingFieldTO extends NewFieldTO {
    private final Long id;

    @Builder
    public ExistingFieldTO(String name, String description, FieldTypeEnum fieldType, List<String> values, Long id) {
        super(name, description, fieldType, values);
        this.id = id;
    }
}


package com.gmw.view.tos;

import com.gmw.view.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingFieldTO extends NewFieldTO {
    private Long id;

    @Builder
    public ExistingFieldTO(String name, String description, FieldTypeEnum fieldType, List<String> values, Long id) {
        super(name, description, fieldType, values);
        this.id = id;
    }
}


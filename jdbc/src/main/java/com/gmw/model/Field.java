package com.gmw.model;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private String values;
    private View view;
}

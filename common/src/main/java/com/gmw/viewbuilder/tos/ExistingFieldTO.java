package com.gmw.viewbuilder.tos;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.Builder;

import java.util.List;

@Builder
public class ExistingFieldTO {
    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private List<String> values;
}

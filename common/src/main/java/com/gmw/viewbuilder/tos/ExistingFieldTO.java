package com.gmw.viewbuilder.tos;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExistingFieldTO {
    private Long id;
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private List<String> values;
}


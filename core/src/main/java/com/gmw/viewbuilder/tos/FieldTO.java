package com.gmw.viewbuilder.tos;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FieldTO {
    private String name;
    private FieldTypeEnum fieldType;
    private List<String> values;
}

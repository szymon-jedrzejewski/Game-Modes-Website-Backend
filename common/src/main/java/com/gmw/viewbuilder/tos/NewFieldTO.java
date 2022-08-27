package com.gmw.viewbuilder.tos;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class NewFieldTO {
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private List<String> values;
}

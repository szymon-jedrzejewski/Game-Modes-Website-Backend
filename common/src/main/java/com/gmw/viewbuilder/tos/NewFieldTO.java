package com.gmw.viewbuilder.tos;

import com.gmw.viewbuilder.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFieldTO {
    private String name;
    private String description;
    private FieldTypeEnum fieldType;
    private List<String> values;
}

package com.gmw.fieldvalue.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFieldValueTO {
    private Long fieldId;
    private Long modId;
    private String value;
}

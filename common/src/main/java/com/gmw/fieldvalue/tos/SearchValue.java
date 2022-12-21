package com.gmw.fieldvalue.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchValue {
    private Long fieldId;
    private String value;
    private boolean exactMatch;
}

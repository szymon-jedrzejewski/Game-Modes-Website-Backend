package com.gmw.fieldvalue.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchFieldValue {
    private Long fieldId;
    private String value;
    private boolean isExact;
}

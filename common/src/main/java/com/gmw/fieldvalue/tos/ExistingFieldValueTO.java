package com.gmw.fieldvalue.tos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExistingFieldValueTO extends NewFieldValueTO {
    private Long id;

    @Builder
    public ExistingFieldValueTO(Long fieldId, Long modId, String value, Long id) {
        super(fieldId, modId, value);
        this.id = id;
    }
}

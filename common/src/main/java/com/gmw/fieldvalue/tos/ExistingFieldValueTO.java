package com.gmw.fieldvalue.tos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ExistingFieldValueTO extends NewFieldValueTO {
    private final Long id;

    @Builder
    public ExistingFieldValueTO(Long fieldId, Long modId, String value, Long id) {
        super(fieldId, modId, value);
        this.id = id;
    }
}

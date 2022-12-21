package com.gmw.coverters;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.model.FieldValue;

public class FieldValueConverter implements
        ModelConverter<ExistingFieldValueTO, FieldValue>,
        TOConverter<NewFieldValueTO, FieldValue> {

    @Override
    public ExistingFieldValueTO convertToTO(FieldValue fieldValue) {
        return ExistingFieldValueTO
                .builder()
                .id(fieldValue.getFieldId())
                .fieldId(fieldValue.getFieldId())
                .modId(fieldValue.getModId())
                .value(fieldValue.getValue())
                .build();
    }

    @Override
    public FieldValue convertToModel(NewFieldValueTO newFieldValueTO) {
        FieldValue field = new FieldValue();
        field.setFieldId(newFieldValueTO.getFieldId());
        field.setModId(newFieldValueTO.getModId());
        field.setValue(newFieldValueTO.getValue());

        return field;
    }
}

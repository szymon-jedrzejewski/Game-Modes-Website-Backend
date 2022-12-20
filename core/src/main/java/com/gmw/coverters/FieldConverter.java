package com.gmw.coverters;

import com.gmw.model.Field;
import com.gmw.view.enums.FieldTypeEnum;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.NewFieldTO;

public class FieldConverter implements
        ModelConverter<ExistingFieldTO, Field>,
        TOConverter<NewFieldTO, Field> {

    @Override
    public ExistingFieldTO convertToTO(Field field) {
        return ExistingFieldTO
                .builder()
                .id(field.getId())
                .fieldType(FieldTypeEnum.valueOf(field.getType()))
                .description(field.getDescription())
                .label(field.getLabel())
                .build();
    }

    @Override
    public Field convertToModel(NewFieldTO newFieldTO) {
        Field field = new Field("fields");
        field.setType(newFieldTO.getFieldType().toString());
        field.setDescription(newFieldTO.getDescription());
        field.setLabel(newFieldTO.getLabel());

        return field;
    }
}

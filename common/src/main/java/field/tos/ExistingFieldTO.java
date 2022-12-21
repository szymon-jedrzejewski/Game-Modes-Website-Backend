package field.tos;

import field.enums.FieldTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExistingFieldTO extends NewFieldTO {
    private final Long id;

    @Builder
    public ExistingFieldTO(String description, FieldTypeEnum fieldType, String label, Long id) {
        super(description, fieldType, label);
        this.id = id;
    }
}


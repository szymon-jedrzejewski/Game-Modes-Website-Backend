package field.tos;

import field.enums.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFieldTO {
    private String description;
    private FieldTypeEnum fieldType;
    private String label;
}

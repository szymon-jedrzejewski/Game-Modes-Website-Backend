package com.gmw.mod.tos;

import com.gmw.fieldvalue.tos.NewFieldValueTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewModTO {
    private String name;
    private Long userId;
    private Long gameId;
    private Long categoryId;
    private String description;
    private String downloadLink;
    private List<NewFieldValueTO> fieldsValues;
    private Date date;
    private byte[] avatar;
}

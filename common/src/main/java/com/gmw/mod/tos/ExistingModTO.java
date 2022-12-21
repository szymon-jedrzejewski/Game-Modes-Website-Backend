package com.gmw.mod.tos;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class ExistingModTO {
    private Long id;
    private String name;
    private Long userId;
    private Long gameId;
    private Long categoryId;
    private String description;
    private String downloadLink;
    @Setter
    private List<ExistingFieldValueTO> fieldsValues;
    private Date date;
    private byte[] avatar;
}

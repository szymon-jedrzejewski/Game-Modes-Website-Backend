package com.gmw.api.rest.activity.mod.tos;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;

import java.util.Date;
import java.util.List;

public record ModDTO(String name,
                     String userName,
                     String game,
                     String description,
                     String downloadLink,
                     List<ExistingFieldValueTO> fieldsValues,
                     Date date,
                     String category,
                     byte[] avatar,
                     List<CommentDTO> comments,
                     Double rating) {
}

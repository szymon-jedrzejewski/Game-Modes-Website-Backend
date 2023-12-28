package com.gmw.mod.tos;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
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

    public ExistingModTO() {
    }

    public ExistingModTO(Long id, String name, Long userId, Long gameId, Long categoryId, String description, String downloadLink, Date date, byte[] avatar) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.gameId = gameId;
        this.categoryId = categoryId;
        this.description = description;
        this.downloadLink = downloadLink;
        this.date = date;
        this.avatar = avatar;
    }
}

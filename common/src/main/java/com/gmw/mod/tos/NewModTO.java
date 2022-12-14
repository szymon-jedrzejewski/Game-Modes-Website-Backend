package com.gmw.mod.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date date;
    private byte[] avatar;
}

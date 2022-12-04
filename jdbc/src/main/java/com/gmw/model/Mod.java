package com.gmw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Mod extends Table{
    private Long id;
    private String name;
    private Long userId;
    private Long gameId;
    private Long categoryId;
    private String description;
    private String downloadLink;
    private Date date;
    private byte[] avatar;


    public Mod() {
        super(null);
    }

    public Mod(String tableName) {
        super(tableName);
    }

    public Mod(String tableName, Long id, String name, Long userId, Long gameId, Long categoryId, String description, String downloadLink, Date date, byte[] avatar) {
        super(tableName);
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

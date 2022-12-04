package com.gmw.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Comment extends Table{
    private Long id;
    private Long userId;
    private Long modId;
    private String comment;


    public Comment() {
        super(null);
    }

    public Comment(String tableName) {
        super(tableName);
    }

    public Comment(String tableName, Long id, Long userId, Long modId, String comment) {
        super(tableName);
        this.id = id;
        this.userId = userId;
        this.modId = modId;
        this.comment = comment;
    }
}

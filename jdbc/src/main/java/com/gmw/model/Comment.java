package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Comment extends Table implements Persistable {
    private Long id;
    private Long userId;
    private Long modId;
    private String comment;
    private Timestamp creationDate;


    public Comment() {
        super("comments");
    }
}

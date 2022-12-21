package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Comment extends Table implements Persistable {
    private Long id;
    private Long userId;
    private Long modId;
    private String comment;


    public Comment() {
        super("comments");
    }
}

package com.gmw.comment.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingCommentTO extends NewCommentTO {
    private Long id;
    private Timestamp creationDate;

    @Builder
    public ExistingCommentTO(Long userId, Long modId, String comment, Long id, Timestamp creationDate) {
        super(userId, modId, comment);
        this.id = id;
        this.creationDate = creationDate;
    }
}

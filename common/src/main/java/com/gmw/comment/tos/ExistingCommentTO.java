package com.gmw.comment.tos;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingCommentTO extends NewCommentTO {
    private Long id;

    @Builder
    public ExistingCommentTO(Long userId, Long modId, String comment, Long id) {
        super(userId, modId, comment);
        this.id = id;
    }
}

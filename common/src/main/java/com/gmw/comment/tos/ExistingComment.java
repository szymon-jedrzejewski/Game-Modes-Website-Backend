package com.gmw.comment.tos;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingComment extends NewComment {
    private Long id;

    @Builder
    public ExistingComment(Long userId, Long modId, String comment, Long id) {
        super(userId, modId, comment);
        this.id = id;
    }
}

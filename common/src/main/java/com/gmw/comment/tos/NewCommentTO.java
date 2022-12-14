package com.gmw.comment.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentTO {
    private Long userId;
    private Long modId;
    private String comment;
}

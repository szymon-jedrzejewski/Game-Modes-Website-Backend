package com.gmw.coverters;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.model.Comment;

public class CommentConverter implements
        ModelConverter<ExistingCommentTO, Comment>,
        TOConverter<NewCommentTO, Comment> {

    @Override
    public ExistingCommentTO convertToTO(Comment comment) {
        return ExistingCommentTO
                .builder()
                .id(comment.getId())
                .modId(comment.getModId())
                .userId(comment.getUserId())
                .comment(comment.getComment())
                .build();
    }

    @Override
    public Comment convertToModel(NewCommentTO newCommentTO) {
        Comment comment = new Comment("comments");
        comment.setComment(newCommentTO.getComment());
        comment.setModId(newCommentTO.getModId());
        comment.setUserId(newCommentTO.getUserId());
        return comment;
    }
}

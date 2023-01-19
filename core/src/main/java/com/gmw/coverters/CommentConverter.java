package com.gmw.coverters;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.model.Comment;

import java.sql.Timestamp;

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
                .creationDate(comment.getCreationDate())
                .build();
    }

    @Override
    public Comment convertToModel(NewCommentTO newCommentTO) {
        Comment comment = new Comment();
        comment.setComment(newCommentTO.getComment());
        comment.setModId(newCommentTO.getModId());
        comment.setUserId(newCommentTO.getUserId());
        comment.setCreationDate(new Timestamp(System.currentTimeMillis()));

        return comment;
    }
}

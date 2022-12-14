package com.gmw.services.comment;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBCommentService {
    void createComment(NewCommentTO newCommentTO) throws ResourceNotCreatedException;
    void updateComment(ExistingCommentTO existingCommentTO) throws ResourceNotUpdatedException;
    void deleteComment(Long CommentId) throws ResourceNotDeletedException;
}

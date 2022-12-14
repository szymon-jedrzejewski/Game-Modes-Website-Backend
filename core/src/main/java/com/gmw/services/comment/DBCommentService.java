package com.gmw.services.comment;

import com.gmw.comment.tos.ExistingComment;
import com.gmw.comment.tos.NewComment;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBCommentService {
    void createComment(NewComment newComment) throws ResourceNotCreatedException;
    void updateComment(ExistingComment existingComment) throws ResourceNotUpdatedException;
    void deleteComment(Long CommentId) throws ResourceNotDeletedException;
}

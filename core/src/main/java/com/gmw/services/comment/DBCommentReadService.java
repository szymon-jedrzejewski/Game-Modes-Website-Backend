package com.gmw.services.comment;

import com.gmw.comment.tos.ExistingComment;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBCommentReadService {
    List<ExistingComment> findCommentsByModId(Long modId) throws ResourceNotFoundException;
}

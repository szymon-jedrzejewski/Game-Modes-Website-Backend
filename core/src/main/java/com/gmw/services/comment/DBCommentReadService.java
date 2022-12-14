package com.gmw.services.comment;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBCommentReadService {
    List<ExistingCommentTO> findCommentsByModId(Long modId) throws ResourceNotFoundException;
}

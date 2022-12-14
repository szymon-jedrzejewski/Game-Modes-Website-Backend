package com.gmw.services.comment.impl;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.model.Comment;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public class DBCommentServiceImpl extends DBCommentReadServiceImpl implements DBCommentService {
    public DBCommentServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createComment(NewCommentTO newCategory) throws ResourceNotCreatedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        Comment comment = new Comment();
        comment.setComment(newCategory.getComment());
        comment.setModId(newCategory.getModId());
        comment.setUserId(newCategory.getUserId());

        ServiceUtils.create(repository, comment);
    }

    @Override
    public void updateComment(ExistingCommentTO existingCommentTO) throws ResourceNotUpdatedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        Comment comment = new Comment();
        comment.setId(existingCommentTO.getId());
        comment.setComment(existingCommentTO.getComment());
        comment.setModId(existingCommentTO.getModId());
        comment.setUserId(existingCommentTO.getUserId());

        ServiceUtils.update(repository, comment);
    }

    @Override
    public void deleteComment(Long commentId) throws ResourceNotDeletedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        ServiceUtils.delete(repository, commentId);
    }
}

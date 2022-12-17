package com.gmw.services.comment.impl;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.coverters.CommentConverter;
import com.gmw.coverters.TOConverter;
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
    public void createComment(NewCommentTO newComment) throws ResourceNotCreatedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();
        TOConverter<NewCommentTO, Comment> converter = new CommentConverter();

        Comment comment = converter.convertToModel(newComment);

        ServiceUtils.create(repository, comment);
    }

    @Override
    public void updateComment(ExistingCommentTO existingCommentTO) throws ResourceNotUpdatedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();
        TOConverter<NewCommentTO, Comment> converter = new CommentConverter();

        Comment comment = converter.convertToModel(existingCommentTO);
        comment.setId(existingCommentTO.getId());

        ServiceUtils.update(repository, comment);
    }

    @Override
    public void deleteComment(Long commentId) throws ResourceNotDeletedException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        ServiceUtils.delete(repository, commentId);
    }
}

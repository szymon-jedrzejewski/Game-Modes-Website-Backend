package com.gmw.services.comment.impl;

import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.model.Comment;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.TOConverter;
import com.gmw.services.comment.DBCommentReadService;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public class DBCommentReadServiceImpl extends DBService implements DBCommentReadService, TOConverter<ExistingCommentTO, Comment> {
    public DBCommentReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public List<ExistingCommentTO> findCommentsByModId(Long modId) throws ResourceNotFoundException {
        Repository<Comment> repository = getRepositoryManager().getCommentRepository();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("comments");
        querySpec.setClazz(Comment.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("mod_id", Operator.EQUAL_TO, modId));

        return ServiceUtils.find(repository, this, querySpec);
    }

    @Override
    public ExistingCommentTO convert(Comment comment) {
        return ExistingCommentTO
                .builder()
                .id(comment.getId())
                .modId(comment.getModId())
                .userId(comment.getUserId())
                .comment(comment.getComment())
                .build();
    }
}

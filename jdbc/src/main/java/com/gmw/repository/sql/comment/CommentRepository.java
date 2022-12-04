package com.gmw.repository.sql.comment;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Comment;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CommentRepository implements Repository<Comment> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    public CommentRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Comment newComment) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new comment!");
            Comment comment = (Comment) persistenceManager.create(newComment);
            Long id = comment.getId();
            LOGGER.debug("New comment with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating comment! " + newComment, e);
        }
        throw new SqlRepositoryException();
    }

    @Override
    public void update(Comment comment) {
        persistenceManager.update(comment);
        LOGGER.debug("Comment with id " + comment.getId() + " was updated!");
    }

    @Override
    public void delete(Long id) {
        persistenceManager.delete(id, "comments");
        LOGGER.debug("Comment with id " + id + " was deleted!");
    }

    @Override
    public List<Comment> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(Comment.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
        }
        throw new SqlRepositoryException();
    }
}

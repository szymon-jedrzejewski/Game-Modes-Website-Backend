package com.gmw.repository;


import com.gmw.model.*;

public interface RepositoryManager {

    Repository<View> getViewRepository();
    Repository<Field> getFieldRepository();
    Repository<Game> getGameRepository();
    Repository<Category> getCategoryRepository();
    Repository<Comment> getCommentRepository();
    Repository<Mod> getModRepository();
    Repository<Rating> getRatingRepository();
    Repository<User> getUserRepository();
    Repository<FieldValue> getFieldValueRepository();
}

package com.gmw.repository.sql.category;

import com.gmw.exceptions.SqlPersistenceManagerException;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Category;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import com.gmw.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryRepository implements Repository<Category> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final PersistenceManager persistenceManager;

    public CategoryRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Category newCategory) throws SqlRepositoryException {
        try {
            LOGGER.debug("Creating new category!");
            Category category = (Category) persistenceManager.create(newCategory);
            Long id = category.getId();
            LOGGER.debug("New category with id " + id + " was created!");
            return id;
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during creating category! " + newCategory, e);
        }
        throw new SqlRepositoryException();
    }

    @Override
    public void update(Category category) {
        persistenceManager.update(category);
        LOGGER.debug("Category with id " + category.getId() + " was updated!");
    }

    @Override
    public void delete(Long id) {
        persistenceManager.delete(id, "categories");
        LOGGER.debug("Category with id " + id + " was deleted!");
    }

    @Override
    public List<Category> find(QuerySpec querySpec) throws SqlRepositoryException {
        try {
            return persistenceManager
                    .find(querySpec)
                    .stream()
                    .map(Category.class::cast)
                    .toList();
        } catch (SqlPersistenceManagerException e) {
            LOGGER.error("Error during searching values!", e);
        }
        throw new SqlRepositoryException();
    }
}

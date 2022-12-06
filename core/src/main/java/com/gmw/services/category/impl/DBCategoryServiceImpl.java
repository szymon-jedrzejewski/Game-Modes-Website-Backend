package com.gmw.services.category.impl;

import com.gmw.category.tos.ExistingCategory;
import com.gmw.category.tos.NewCategory;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Category;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.category.DBCategoryService;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBCategoryServiceImpl extends DBCategoryReadServiceImpl implements DBCategoryService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBCategoryServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createCategory(NewCategory newCategory) throws ResourceNotCreatedException {

        Repository<Category> repository = getRepositoryManager().getCategoryRepository();

        Category category = new Category("categories");
        category.setName(newCategory.getName());

        ServiceUtils.create(repository, category);
    }

    @Override
    public void updateCategory(ExistingCategory existingCategory) throws ResourceNotUpdatedException {
        Category category = new Category("categories", existingCategory.getId(), existingCategory.getName());

        try {
            getRepositoryManager().getCategoryRepository().update(category);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot update category!");
            throw new ResourceNotUpdatedException();
        }
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceNotDeletedException {
        try {
            getRepositoryManager().getCategoryRepository().delete(categoryId);
        } catch (SqlRepositoryException e) {
            LOGGER.error("Cannot delete category.");
            throw new ResourceNotDeletedException();
        }
    }
}

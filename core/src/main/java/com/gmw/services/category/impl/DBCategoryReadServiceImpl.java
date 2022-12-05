package com.gmw.services.category.impl;

import com.gmw.category.tos.ExistingCategory;
import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Category;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.category.DBCategoryReadService;
import com.gmw.services.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class DBCategoryReadServiceImpl extends DBService implements DBCategoryReadService {
    private static final Logger LOGGER = LogManager.getLogger();

    public DBCategoryReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingCategory obtainCategoryByName(String name) throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, name));

        try {
            Category category = getRepositoryManager().getCategoryRepository().find(querySpec).get(0);
            ExistingCategory existingCategory = mapCategory(category);

            if (existingCategory == null) {
                throw new ResourceNotFoundException();
            }

            return existingCategory;
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining category with name: " + name);
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public ExistingCategory obtainCategoryById(Long id) throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));

        try {
            Category category = getRepositoryManager().getCategoryRepository().find(querySpec).get(0);
            ExistingCategory existingCategory = mapCategory(category);

            if (existingCategory == null) {
                throw new ResourceNotFoundException();
            }

            return existingCategory;
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining category with id: " + id);
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public List<ExistingCategory> obtainCategories() throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);

        try {
            return getRepositoryManager()
                    .getCategoryRepository()
                    .find(querySpec)
                    .stream()
                    .map(this::mapCategory)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (SqlRepositoryException e) {
            LOGGER.error("Error during obtaining categories!");
            throw new ResourceNotFoundException();
        }
    }

    private ExistingCategory mapCategory(Category category) {
        if (category != null) {
            return ExistingCategory
                    .builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }

        return null;
    }
}

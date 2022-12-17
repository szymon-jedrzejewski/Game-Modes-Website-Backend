package com.gmw.services.category.impl;

import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.coverters.CategoryConverter;
import com.gmw.model.Category;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.ServiceUtils;
import com.gmw.services.category.DBCategoryReadService;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public class DBCategoryReadServiceImpl extends DBService implements DBCategoryReadService {

    public DBCategoryReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingCategoryTO obtainCategoryByName(String name) throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, name));

        Repository<Category> repository = getRepositoryManager().getCategoryRepository();

        return ServiceUtils.find(repository, new CategoryConverter(), querySpec).get(0);
    }

    @Override
    public ExistingCategoryTO obtainCategoryById(Long id) throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);
        querySpec.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));

        Repository<Category> repository = getRepositoryManager().getCategoryRepository();

        return ServiceUtils.find(repository, new CategoryConverter(), querySpec).get(0);
    }

    @Override
    public List<ExistingCategoryTO> obtainCategories() throws ResourceNotFoundException {
        QuerySpec querySpec = new QuerySpec();
        querySpec.setTableName("categories");
        querySpec.setClazz(Category.class);

        Repository<Category> repository = getRepositoryManager().getCategoryRepository();

        return ServiceUtils.find(repository, new CategoryConverter(), querySpec);
    }
}

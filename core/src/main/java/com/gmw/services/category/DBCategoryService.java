package com.gmw.services.category;

import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.category.tos.NewCategoryTO;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBCategoryService {
    void createCategory(NewCategoryTO newCategoryTO) throws ResourceNotCreatedException;
    void updateCategory(ExistingCategoryTO existingCategory) throws ResourceNotUpdatedException;
    void deleteCategory(Long categoryId) throws ResourceNotDeletedException;
}

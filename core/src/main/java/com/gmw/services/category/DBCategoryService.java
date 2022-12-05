package com.gmw.services.category;

import com.gmw.category.tos.ExistingCategory;
import com.gmw.category.tos.NewCategory;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBCategoryService {
    void createCategory(NewCategory newCategory) throws ResourceNotCreatedException;
    void updateCategory(ExistingCategory existingCategory) throws ResourceNotUpdatedException;
    void deleteCategory(Long categoryId) throws ResourceNotDeletedException;
}

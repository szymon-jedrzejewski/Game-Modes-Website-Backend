package com.gmw.services.category;

import com.gmw.category.tos.ExistingCategory;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBCategoryReadService {
    ExistingCategory obtainCategoryByName(String name) throws ResourceNotFoundException;
    ExistingCategory obtainCategoryById(Long id) throws ResourceNotFoundException;
    List<ExistingCategory> obtainCategories() throws ResourceNotFoundException;
}

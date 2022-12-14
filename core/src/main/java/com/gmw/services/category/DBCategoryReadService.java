package com.gmw.services.category;

import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBCategoryReadService {
    ExistingCategoryTO obtainCategoryByName(String name) throws ResourceNotFoundException;
    ExistingCategoryTO obtainCategoryById(Long id) throws ResourceNotFoundException;
    List<ExistingCategoryTO> obtainCategories() throws ResourceNotFoundException;
}

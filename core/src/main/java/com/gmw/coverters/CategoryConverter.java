package com.gmw.coverters;

import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.category.tos.NewCategoryTO;
import com.gmw.model.Category;

public class CategoryConverter implements
        ModelConverter<ExistingCategoryTO, Category>,
        TOConverter<NewCategoryTO, Category> {
    @Override
    public ExistingCategoryTO convertToTO(Category category) {
        return ExistingCategoryTO
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category convertToModel(NewCategoryTO newCategoryTO) {
        Category category = new Category("categories");
        category.setName(newCategoryTO.getName());
        return category;
    }
}

package com.rovlkr.documentbase.mapping;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.model.Category;

public class CategoryMapper {

    public Category entityToModel(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());

        return category;
    }

    public CategoryEntity modelToEntity(Category model) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());

        return entity;
    }
}

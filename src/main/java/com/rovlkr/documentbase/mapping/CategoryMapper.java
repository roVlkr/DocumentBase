package com.rovlkr.documentbase.mapping;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toModel(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setName(entity.getName());

        return category;
    }

    public CategoryEntity toEntity(Category model) {
        CategoryEntity entity = new CategoryEntity(model.getName());
        entity.setId(model.getId());

        return entity;
    }

    public CategoryEntity toEntity(String categoryName) {
        return new CategoryEntity(categoryName);
    }
}

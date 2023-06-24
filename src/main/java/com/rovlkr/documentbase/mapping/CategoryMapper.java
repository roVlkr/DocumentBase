package com.rovlkr.documentbase.mapping;

import org.mapstruct.Mapper;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toModel(CategoryEntity entity);

    CategoryEntity toEntity(Category model);

    default CategoryEntity toEntity(String categoryName) {
        return CategoryEntity.builder().name(categoryName).build();
    }
}

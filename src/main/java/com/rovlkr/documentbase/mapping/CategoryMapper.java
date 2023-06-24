package com.rovlkr.documentbase.mapping;

import org.mapstruct.Mapper;

import com.rovlkr.documentbase.entity.Category;
import com.rovlkr.documentbase.dto.CategoryDTO;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category entity);

    Category toEntity(CategoryDTO dto);

    default Category toEntity(String categoryName) {
        return Category.builder().name(categoryName).build();
    }
}

package com.rovlkr.documentbase.service;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.mapping.CategoryMapper;
import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryMapper mapper, CategoryRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Long createCategory(String categoryName) {
        CategoryEntity entity = mapper.toEntity(categoryName);
        return repository.save(entity).getId();
    }

    public Set<Category> getAllCategories() {
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toSet());
    }

    public Optional<Category> getCategory(Long categoryId) {
        return repository.findById(categoryId).map(mapper::toModel);
    }
}

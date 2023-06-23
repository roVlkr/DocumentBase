package com.rovlkr.documentbase.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public Long createCategory(CategoryEntity entity) {
        return repository.save(entity).getId();
    }

    public Stream<CategoryEntity> getAllCategories() {
        return repository.findAll().stream();
    }

    public Optional<CategoryEntity> getCategory(Long categoryId) {
        return repository.findById(categoryId);
    }
}

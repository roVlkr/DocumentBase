package com.rovlkr.documentbase.service;

import java.util.Optional;
import java.util.stream.Stream;

import com.rovlkr.documentbase.entity.Category;
import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public Long createCategory(Category entity) {
        return repository.save(entity).getId();
    }

    public Stream<Category> getAllCategories() {
        return repository.findAll().stream();
    }

    public Optional<Category> getCategory(Long categoryId) {
        return repository.findById(categoryId);
    }
}

package com.rovlkr.documentbase.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService service;

    @Autowired
    public CategoryResource(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createCategory(@RequestBody String categoryName) {
        Long id = service.createCategory(categoryName);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Category>> getAllCategories() {
        Set<Category> categories = service.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long categoryId) {
        return service.getCategory(categoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

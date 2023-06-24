package com.rovlkr.documentbase.rest;

import java.util.Set;
import java.util.stream.Collectors;

import com.rovlkr.documentbase.dto.CategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovlkr.documentbase.mapping.CategoryMapper;
import com.rovlkr.documentbase.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @PostMapping
    public ResponseEntity<Long> createCategory(@RequestBody String categoryName) {
        Long id = service.createCategory(mapper.toEntity(categoryName));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<CategoryDTO>> getAllCategories() {
        Set<CategoryDTO> categories = service.getAllCategories().map(mapper::toDTO).collect(Collectors.toSet());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Long categoryId) {
        return service.getCategory(categoryId).map(mapper::toDTO).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

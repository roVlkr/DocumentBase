package com.rovlkr.documentbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovlkr.documentbase.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {}

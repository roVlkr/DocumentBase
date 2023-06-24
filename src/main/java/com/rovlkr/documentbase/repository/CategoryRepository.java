package com.rovlkr.documentbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovlkr.documentbase.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

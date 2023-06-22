package com.rovlkr.documentbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rovlkr.documentbase.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {}

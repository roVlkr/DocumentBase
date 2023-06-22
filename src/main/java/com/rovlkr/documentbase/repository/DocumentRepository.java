package com.rovlkr.documentbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rovlkr.documentbase.entity.DocumentEntity;

@Repository
public interface DocumentRepository
        extends JpaRepository<DocumentEntity, Long>, JpaSpecificationExecutor<DocumentEntity> {}

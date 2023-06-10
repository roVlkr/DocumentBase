package com.rovlkr.documentbase.repository;

import com.rovlkr.documentbase.entity.DocumentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository extends PagingAndSortingRepository<DocumentEntity, Long> {

}

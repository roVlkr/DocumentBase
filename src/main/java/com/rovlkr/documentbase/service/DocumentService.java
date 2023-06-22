package com.rovlkr.documentbase.service;

import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentContainsTags;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentHasCategory;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentHasSensibility;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentMatchesText;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Set;
import java.util.stream.Collectors;

import com.rovlkr.documentbase.model.NewDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.mapping.DocumentMapper;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.repository.DocumentRepository;

@Service
public class DocumentService {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final DocumentMapper mapper;
    private final DocumentRepository repository;

    @Autowired
    public DocumentService(DocumentMapper mapper, DocumentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Long createDocument(NewDocument newDocument) {
        DocumentEntity entity = mapper.toEntity(newDocument);
        return repository.save(entity).getId();
    }

    public Set<Document> searchDocuments(String text, Long categoryId, Set<Long> tagIds, Boolean sensible,
            Integer limit) {
        if (limit == null) {
            limit = DEFAULT_PAGE_SIZE;
        }

        Pageable page = PageRequest.ofSize(limit);
        // @formatter:off
        Page<DocumentEntity> documentEntities = repository.findAll(
                where(documentMatchesText(text)
                        .and(documentHasCategory(categoryId))
                        .and(documentContainsTags(tagIds))
                        .and(documentHasSensibility(sensible))),
                page);
        // @formatter:on
        return documentEntities.get().map(mapper::toModel).collect(Collectors.toSet());
    }
}

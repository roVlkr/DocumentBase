package com.rovlkr.documentbase.service;

import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentContainsTags;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentHasCategory;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentHasSensibility;
import static com.rovlkr.documentbase.repository.DocumentSpecifications.documentMatchesText;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.repository.DocumentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DocumentService {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final DocumentRepository repository;

    public Long createDocument(DocumentEntity document) {
        return repository.save(document).getId();
    }

    public String uploadFile(DocumentEntity document, MultipartFile file) {
        return null;
    }

    public Stream<DocumentEntity> searchDocuments(String text, Long categoryId, Set<Long> tagIds, Boolean sensible,
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
        return documentEntities.get();
    }

    public Optional<DocumentEntity> getDocument(Long id) {
        return repository.findById(id);
    }
}

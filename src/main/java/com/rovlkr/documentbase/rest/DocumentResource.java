package com.rovlkr.documentbase.rest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.mapping.DocumentMapper;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.service.DocumentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
@ConfigurationProperties(prefix = "documents")
public class DocumentResource {

    private final DocumentService service;
    private final DocumentMapper mapper;

    @PostMapping
    public ResponseEntity<Long> createDocument(@RequestBody NewDocument newDocument) {
        DocumentEntity entity = mapper.toEntity(newDocument);
        Long id = service.createDocument(entity);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadFile(@PathVariable("id") Long documentId, @RequestParam MultipartFile file) {
        Optional<DocumentEntity> document = service.getDocument(documentId);
        if (document.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (document.get().getFileLocation() != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        String uploadFilePath = service.uploadFile(document.get(), file);
        return ResponseEntity.ok(uploadFilePath);
    }

    @GetMapping
    public ResponseEntity<Set<Document>> searchDocuments(@RequestParam(required = false) String text,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "tags", required = false) Set<Long> tagIds,
            @RequestParam(required = false) Boolean sensible, @RequestParam(required = false) Integer limit) {
        Stream<DocumentEntity> documents = service.searchDocuments(text, categoryId, tagIds, sensible, limit);
        return ResponseEntity.ok(documents.map(mapper::toModel).collect(Collectors.toSet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable("id") Long documentId) {
        return service.getDocument(documentId).map(mapper::toModel).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

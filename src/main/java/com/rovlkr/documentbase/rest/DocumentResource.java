package com.rovlkr.documentbase.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.service.DocumentService;

@RestController
@RequestMapping("/documents")
public class DocumentResource {

    private final DocumentService service;

    @Autowired
    public DocumentResource(DocumentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createDocument(@RequestBody NewDocument newDocument) {
        Long id = service.createDocument(newDocument);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Document>> searchDocuments(@RequestParam(required = false) String text,
            @RequestParam(name = "category", required = false) Long categoryId,
            @RequestParam(name = "tags", required = false) Set<Long> tagIds,
            @RequestParam(required = false) Boolean sensible, @RequestParam(required = false) Integer limit) {
        Set<Document> documents = service.searchDocuments(text, categoryId, tagIds, sensible, limit);
        return ResponseEntity.ok(documents);
    }


}

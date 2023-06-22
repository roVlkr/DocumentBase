package com.rovlkr.documentbase.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovlkr.documentbase.model.Tag;
import com.rovlkr.documentbase.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagResource {

    private final TagService service;

    @Autowired
    public TagResource(TagService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Long> createTag(@RequestBody String tagName) {
        Long id = service.createTag(tagName);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Tag>> getAllTags() {
        Set<Tag> tags = service.getAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable("id") Long tagId) {
        return service.getTag(tagId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

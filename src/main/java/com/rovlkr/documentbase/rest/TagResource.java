package com.rovlkr.documentbase.rest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.mapping.TagMapper;
import com.rovlkr.documentbase.model.Tag;
import com.rovlkr.documentbase.service.TagService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tags")
public class TagResource {

    private final TagMapper mapper;
    private final TagService service;

    @PostMapping
    public ResponseEntity<Long> createTag(@RequestBody String tagName) {
        Long id = service.createTag(mapper.toEntity(tagName));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<Tag>> getAllTags() {
        List<TagEntity> tags = service.getAllTags();
        return new ResponseEntity<>(tags.stream().map(mapper::toModel).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable("id") Long tagId) {
        return service.getTag(tagId).map(mapper::toModel).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

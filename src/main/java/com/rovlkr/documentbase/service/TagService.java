package com.rovlkr.documentbase.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.mapping.TagMapper;
import com.rovlkr.documentbase.model.Tag;
import com.rovlkr.documentbase.repository.TagRepository;

@Service
public class TagService {

    private final TagMapper mapper;
    private final TagRepository repository;

    @Autowired
    public TagService(TagMapper mapper, TagRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Long createTag(String tagName) {
        TagEntity entity = mapper.toEntity(tagName);
        return repository.save(entity).getId();
    }

    public Set<Tag> getAllTags() {
        return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toSet());
    }

    public Optional<Tag> getTag(Long tagId) {
        return repository.findById(tagId).map(mapper::toModel);
    }
}

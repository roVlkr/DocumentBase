package com.rovlkr.documentbase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository repository;

    public Long createTag(TagEntity tag) {
        return repository.save(tag).getId();
    }

    public List<TagEntity> getAllTags() {
        return repository.findAll();
    }

    public Optional<TagEntity> getTag(Long tagId) {
        return repository.findById(tagId);
    }
}

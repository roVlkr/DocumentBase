package com.rovlkr.documentbase.service;

import java.util.List;
import java.util.Optional;

import com.rovlkr.documentbase.entity.Tag;
import org.springframework.stereotype.Service;

import com.rovlkr.documentbase.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository repository;

    public Long createTag(Tag tag) {
        return repository.save(tag).getId();
    }

    public List<Tag> getAllTags() {
        return repository.findAll();
    }

    public Optional<Tag> getTag(Long tagId) {
        return repository.findById(tagId);
    }
}

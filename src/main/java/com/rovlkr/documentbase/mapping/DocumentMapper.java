package com.rovlkr.documentbase.mapping;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;

import java.util.stream.Collectors;

public class DocumentMapper {

    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    public DocumentMapper(CategoryMapper categoryMapper, TagMapper tagMapper) {
        this.categoryMapper = categoryMapper;
        this.tagMapper = tagMapper;
    }

    /**
     * Read-Only access for Document-DTO.
     */
    public Document entityToModel(DocumentEntity entity) {
        Document document = new Document();
        document.setId(entity.getId());
        document.setName(entity.getName());
        document.setDescription(entity.getDescription());
        document.setCategory(categoryMapper.entityToModel(entity.getCategory()));
        document.setTags(entity.getTags().stream().map(tagMapper::entityToModel).collect(Collectors.toSet()));

        document.setSensible(entity.isSensible());
        document.setFileLocation(entity.getFileLocation());

        return document;
    }

    /**
     * Write-Only access for NewDocument-DTO.
     */
    public DocumentEntity modelToEntity(NewDocument model) {
        DocumentEntity entity = new DocumentEntity();
        entity.setCategory(categoryMapper.modelToEntity(model.getCategory()));
        entity.setTags(model.getTags().stream().map(tagMapper::modelToEntity).collect(Collectors.toSet()));
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setSensible(model.isSensible());

        return entity;
    }
}

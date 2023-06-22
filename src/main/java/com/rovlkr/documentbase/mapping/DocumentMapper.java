package com.rovlkr.documentbase.mapping;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DocumentMapper {

    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    @Autowired
    public DocumentMapper(CategoryMapper categoryMapper, TagMapper tagMapper) {
        this.categoryMapper = categoryMapper;
        this.tagMapper = tagMapper;
    }

    /**
     * Read-Only access for Document-DTO.
     */
    public Document toModel(DocumentEntity entity) {
        Document document = new Document();
        document.setId(entity.getId());
        document.setName(entity.getName());
        document.setDescription(entity.getDescription());
        document.setCategory(categoryMapper.toModel(entity.getCategory()));
        document.setTags(entity.getTags().stream().map(tagMapper::toModel).collect(Collectors.toSet()));

        document.setSensible(entity.getSensible());
        document.setFileLocation(entity.getFileLocation());

        return document;
    }

    /**
     * Write-Only access for NewDocument-DTO.
     */
    public DocumentEntity toEntity(NewDocument model) {
        DocumentEntity entity = new DocumentEntity();
        entity.setCategory(categoryMapper.toEntity(model.getCategory()));
        entity.setTags(model.getTags().stream().map(tagMapper::toEntity).collect(Collectors.toSet()));
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setSensible(model.isSensible());
        entity.setFileLocation(model.getFileName());

        return entity;
    }
}

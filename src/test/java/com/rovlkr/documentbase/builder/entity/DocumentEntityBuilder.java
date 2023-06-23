package com.rovlkr.documentbase.builder.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.entity.TagEntity;

public class DocumentEntityBuilder {

    private Long id;
    private String name;
    private String description;
    private CategoryEntity category;
    private List<TagEntity> tags;
    private Boolean sensible;
    private String fileLocation;

    private DocumentEntityBuilder() {
        tags = new ArrayList<>();
    }

    public static DocumentEntityBuilder builder() {
        return new DocumentEntityBuilder();
    }

    public DocumentEntityBuilder withDefaultValues() {
        CategoryEntity category = CategoryEntityBuilder.builder().withDefaultValues().build();
        TagEntity tag1 = TagEntityBuilder.builder().withDefaultValues1().build();
        TagEntity tag2 = TagEntityBuilder.builder().withDefaultValues2().build();
        return this.id(TestData.DOCUMENT_ID).name(TestData.DOCUMENT_NAME).description(TestData.DOCUMENT_DESCRIPTION)
                .category(category).addTag(tag1).addTag(tag2).sensible(TestData.DOCUMENT_SENSIBILITY)
                .fileLocation(TestData.DOCUMENT_FILE_LOCATION);
    }

    public DocumentEntityBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DocumentEntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DocumentEntityBuilder description(String description) {
        this.description = description;
        return this;
    }

    public DocumentEntityBuilder category(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public DocumentEntityBuilder tags(List<TagEntity> tags) {
        this.tags = tags;
        return this;
    }

    public DocumentEntityBuilder addTag(TagEntity tag) {
        this.tags.add(tag);
        return this;
    }

    public DocumentEntityBuilder sensible(Boolean sensible) {
        this.sensible = sensible;
        return this;
    }

    public DocumentEntityBuilder fileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public DocumentEntity build() {
        DocumentEntity entity = new DocumentEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setDescription(description);
        entity.setSensible(sensible);
        entity.setCategory(category);
        entity.setTags(new HashSet<>(tags));
        entity.setFileLocation(fileLocation);

        return entity;
    }
}

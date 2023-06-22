package com.rovlkr.documentbase.builder.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.Tag;

public class DocumentBuilder {

    private long id;
    private String name;
    private String description;
    private Category category;
    private List<Tag> tags;
    private boolean sensible;
    private String fileLocation;

    private DocumentBuilder() {
        tags = new ArrayList<>();
    }

    public static DocumentBuilder builder() {
        return new DocumentBuilder();
    }

    public DocumentBuilder withDefaultValues() {
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        Tag tag1 = TagBuilder.builder().withDefaultValues1().build();
        Tag tag2 = TagBuilder.builder().withDefaultValues2().build();
        return this.id(TestData.DOCUMENT_ID).name(TestData.DOCUMENT_NAME).description(TestData.DOCUMENT_DESCRIPTION)
                .category(category).addTag(tag1).addTag(tag2).sensible(TestData.DOCUMENT_SENSIBILITY)
                .fileLocation(TestData.DOCUMENT_FILE_LOCATION);
    }

    public DocumentBuilder id(long id) {
        this.id = id;
        return this;
    }

    public DocumentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public DocumentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public DocumentBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public DocumentBuilder tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public DocumentBuilder addTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public DocumentBuilder sensible(boolean sensible) {
        this.sensible = sensible;
        return this;
    }

    public DocumentBuilder fileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        return this;
    }

    public Document build() {
        Document document = new Document();
        document.setId(id);
        document.setName(name);
        document.setDescription(description);
        document.setSensible(sensible);
        document.setCategory(category);
        document.setTags(new HashSet<>(tags));
        document.setFileLocation(fileLocation);

        return document;
    }
}

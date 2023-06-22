package com.rovlkr.documentbase.builder.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.model.Tag;

public class NewDocumentBuilder {
    private String name;
    private String description;
    private Category category;
    private List<Tag> tags;
    private boolean sensible;
    private String fileName;

    private NewDocumentBuilder() {
        tags = new ArrayList<>();
    }

    public static NewDocumentBuilder builder() {
        return new NewDocumentBuilder();
    }

    public NewDocumentBuilder withDefaultValues() {
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        Tag tag1 = TagBuilder.builder().withDefaultValues1().build();
        Tag tag2 = TagBuilder.builder().withDefaultValues2().build();
        return this.name(TestData.DOCUMENT_NAME).description(TestData.DOCUMENT_DESCRIPTION).category(category)
                .addTag(tag1).addTag(tag2).sensible(TestData.DOCUMENT_SENSIBILITY)
                .fileLocation(TestData.DOCUMENT_FILE_NAME);
    }

    public NewDocumentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public NewDocumentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public NewDocumentBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public NewDocumentBuilder tags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public NewDocumentBuilder addTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public NewDocumentBuilder sensible(boolean sensible) {
        this.sensible = sensible;
        return this;
    }

    public NewDocumentBuilder fileLocation(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public NewDocument build() {
        NewDocument newDocument = new NewDocument();
        newDocument.setName(name);
        newDocument.setDescription(description);
        newDocument.setSensible(sensible);
        newDocument.setCategory(category);
        newDocument.setTags(new HashSet<>(tags));
        newDocument.setFileName(fileName);

        return newDocument;
    }
}

package com.rovlkr.documentbase.builder.model;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.model.Tag;

public class TagBuilder {

    private Long id;
    private String name;

    private TagBuilder() {}

    public static TagBuilder builder() {
        return new TagBuilder();
    }

    public TagBuilder withDefaultValues1() {
        return this.id(TestData.TAG_ID_1).name(TestData.TAG_NAME_1);
    }

    public TagBuilder withDefaultValues2() {
        return this.id(TestData.TAG_ID_2).name(TestData.TAG_NAME_2);
    }

    public TagBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TagBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Tag build() {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);

        return tag;
    }
}

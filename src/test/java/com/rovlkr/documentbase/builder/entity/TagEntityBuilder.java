package com.rovlkr.documentbase.builder.entity;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.entity.TagEntity;

public class TagEntityBuilder {

    private Long id;
    private String name;

    private TagEntityBuilder() {}

    public static TagEntityBuilder builder() {
        return new TagEntityBuilder();
    }

    public TagEntityBuilder withDefaultValues1() {
        return this.id(TestData.TAG_ID_1).name(TestData.TAG_NAME_1);
    }

    public TagEntityBuilder withDefaultValues2() {
        return this.id(TestData.TAG_ID_2).name(TestData.TAG_NAME_2);
    }

    public TagEntityBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TagEntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TagEntity build() {
        return new TagEntity(id, name);
    }
}

package com.rovlkr.documentbase.builder.entity;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.entity.CategoryEntity;

public class CategoryEntityBuilder {

    private Long id;
    private String name;

    private CategoryEntityBuilder() {}

    public static CategoryEntityBuilder builder() {
        return new CategoryEntityBuilder();
    }

    public CategoryEntityBuilder withDefaultValues() {
        return this.id(TestData.CATEGORY_ID).name(TestData.CATEGORY_NAME);
    }

    public CategoryEntityBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CategoryEntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CategoryEntity build() {
        return new CategoryEntity(id, name);
    }
}

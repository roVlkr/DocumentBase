package com.rovlkr.documentbase.builder.model;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.model.Category;

public class CategoryBuilder {

    private Long id;
    private String name;

    private CategoryBuilder() {}

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public CategoryBuilder withDefaultValues() {
        return this.id(TestData.CATEGORY_ID).name(TestData.CATEGORY_NAME);
    }

    public CategoryBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Category build() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }
}

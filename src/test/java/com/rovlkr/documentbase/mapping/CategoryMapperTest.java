package com.rovlkr.documentbase.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.CategoryEntityBuilder;
import com.rovlkr.documentbase.builder.model.CategoryBuilder;
import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.entity.CategoryEntity_;
import com.rovlkr.documentbase.model.Category;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

    @InjectMocks
    private CategoryMapper classUnderTest;

    @Test
    void toEntity_withCategoryName_getsMapped() {
        /// Arrange ///
        String categoryName = TestData.CATEGORY_NAME;

        /// Act ///
        CategoryEntity actualEntity = classUnderTest.toEntity(categoryName);

        /// Assert ///
        assertThat(actualEntity).hasFieldOrPropertyWithValue(CategoryEntity_.ID, null)
                .hasFieldOrPropertyWithValue(CategoryEntity_.NAME, TestData.CATEGORY_NAME);
    }

    @Test
    void toEntity_withCategory_getsMapped() {
        /// Arrange ///
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        CategoryEntity entity = CategoryEntityBuilder.builder().withDefaultValues().build();

        /// Act ///
        CategoryEntity actualEntity = classUnderTest.toEntity(category);

        /// Assert ///
        assertThat(actualEntity).isEqualTo(entity);
    }

    @Test
    void toModel_withEntity_getsMapped() {
        /// Arrange ///
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        CategoryEntity entity = CategoryEntityBuilder.builder().withDefaultValues().build();

        /// Act ///
        Category actualCategory = classUnderTest.toModel(entity);

        /// Assert ///
        assertThat(actualCategory).isEqualTo(category);
    }
}

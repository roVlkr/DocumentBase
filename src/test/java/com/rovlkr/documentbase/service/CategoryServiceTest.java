package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.CategoryEntityBuilder;
import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    private CategoryService classUnderTest;

    @Test
    void createCategory_withCategoryName_createsViaRepository() {
        /// Arrange ///
        CategoryEntity categoryEntity = CategoryEntityBuilder.builder().id(null).name(TestData.CATEGORY_NAME).build();
        CategoryEntity savedEntity = CategoryEntityBuilder.builder().withDefaultValues().build();
        when(repository.save(any(CategoryEntity.class))).thenReturn(savedEntity);

        /// Act ///
        Long id = classUnderTest.createCategory(categoryEntity);

        /// Assert ///
        verify(repository).save(any(CategoryEntity.class));
        assertThat(id).isEqualTo(TestData.CATEGORY_ID);
    }

    @Test
    void getAllCategories_withoutArgs_getsAllViaRepository() {
        /// Arrange ///
        CategoryEntity entity = CategoryEntityBuilder.builder().withDefaultValues().build();
        when(repository.findAll()).thenReturn(List.of(entity));

        /// Act ///
        Stream<CategoryEntity> categories = classUnderTest.getAllCategories();

        /// Assert ///
        verify(repository).findAll();
        assertThat(categories).hasSize(1).first()
                .matches(category -> Objects.equals(category.getName(), entity.getName()));
    }

    @Test
    void getCategory_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        CategoryEntity entity = CategoryEntityBuilder.builder().id(id).name(TestData.CATEGORY_NAME).build();
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        /// Act ///
        Optional<CategoryEntity> actual = classUnderTest.getCategory(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(entity);
    }
}

package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.rovlkr.documentbase.entity.Category;
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
        Category category = TestData.newCategory().build();
        Category savedCategory = TestData.category().build();
        when(repository.save(category)).thenReturn(savedCategory);

        /// Act ///
        Long id = classUnderTest.createCategory(category);

        /// Assert ///
        verify(repository).save(category);
        assertThat(id).isEqualTo(TestData.CATEGORY_ID);
    }

    @Test
    void getAllCategories_withoutArgs_getsAllViaRepository() {
        /// Arrange ///
        Category category = TestData.category().build();
        when(repository.findAll()).thenReturn(List.of(category));

        /// Act ///
        Stream<Category> categories = classUnderTest.getAllCategories();

        /// Assert ///
        verify(repository).findAll();
        assertThat(categories).hasSize(1).first().matches(c -> Objects.equals(c.getName(), category.getName()));
    }

    @Test
    void getCategory_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        Category category = TestData.category().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(category));

        /// Act ///
        Optional<Category> actual = classUnderTest.getCategory(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(category);
    }
}

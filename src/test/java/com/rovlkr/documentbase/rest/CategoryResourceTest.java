package com.rovlkr.documentbase.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Stream;

import com.rovlkr.documentbase.mapping.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.CategoryEntityBuilder;
import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.service.CategoryService;

@WebMvcTest(CategoryResource.class)
class CategoryResourceTest {

    @TestConfiguration
    static class CategoryResourceTestConfiguration {
        @Bean
        public CategoryMapper categoryMapper() {
            return new CategoryMapper();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void createCategory_defaultCategoryName_successful() throws Exception {
        /// Arrange ///
        CategoryEntity categoryEntity = CategoryEntityBuilder.builder().id(null).name(TestData.CATEGORY_NAME).build();
        when(categoryService.createCategory(categoryEntity)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/categories").content(categoryEntity.getName())) //
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(categoryService).createCategory(categoryEntity);
    }

    @Test
    void getAllCategories_noArgs_successful() throws Exception {
        /// Arrange ///
        CategoryEntity categoryEntity = CategoryEntityBuilder.builder().withDefaultValues().build();
        when(categoryService.getAllCategories()).thenReturn(Stream.of(categoryEntity));

        /// Act + Assert ///
        mockMvc.perform(get("/categories")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].name", equalTo(TestData.CATEGORY_NAME)));
        verify(categoryService).getAllCategories();
    }

    @Test
    void getCategory_withId_successful() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        CategoryEntity categoryEntity = CategoryEntityBuilder.builder().withDefaultValues().build();
        when(categoryService.getCategory(id)).thenReturn(Optional.of(categoryEntity));

        /// Act + Assert ///
        mockMvc.perform(get("/categories/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(TestData.CATEGORY_NAME)));
        verify(categoryService).getCategory(id);
    }

    @Test
    void getCategory_withId_notFound() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        when(categoryService.getCategory(id)).thenReturn(Optional.empty());

        /// Act + Assert ///
        mockMvc.perform(get("/categories/" + id)).andExpect(status().isNotFound());
        verify(categoryService).getCategory(id);
    }
}

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
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.model.CategoryBuilder;
import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.service.CategoryService;

@WebMvcTest(CategoryResource.class)
class CategoryResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void createCategory_defaultCategoryName_successful() throws Exception {
        /// Arrange ///
        String categoryName = TestData.CATEGORY_NAME;
        when(categoryService.createCategory(categoryName)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/categories").content(categoryName)) //
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(categoryService).createCategory(categoryName);
    }

    @Test
    void getAllCategories_noArgs_successful() throws Exception {
        /// Arrange ///
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        when(categoryService.getAllCategories()).thenReturn(Set.of(category));

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
        Category category = CategoryBuilder.builder().withDefaultValues().build();
        when(categoryService.getCategory(id)).thenReturn(Optional.of(category));

        /// Act + Assert ///
        mockMvc.perform(get("/categories/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(TestData.CATEGORY_NAME)));
        verify(categoryService).getCategory(id);
    }
}

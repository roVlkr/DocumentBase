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

import java.util.List;
import java.util.Optional;

import com.rovlkr.documentbase.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.service.TagService;

@WebMvcTest(TagResource.class)
@Import(ResourceTestConfiguration.class)
class TagResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Test
    void createTag_defaultTagName_successful() throws Exception {
        /// Arrange ///
        Tag tag = TestData.newTag().build();
        when(tagService.createTag(tag)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/tags").content(tag.getName())) //
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(tagService).createTag(tag);
    }

    @Test
    void getAllCategories_noArgs_successful() throws Exception {
        /// Arrange ///
        Tag tag = TestData.tag1().build();
        when(tagService.getAllTags()).thenReturn(List.of(tag));

        /// Act + Assert ///
        mockMvc.perform(get("/tags")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].name", equalTo(TestData.TAG_NAME_1)));
        verify(tagService).getAllTags();
    }

    @Test
    void getTag_withId_successful() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        Tag tag = TestData.tag1().build();
        when(tagService.getTag(id)).thenReturn(Optional.of(tag));

        /// Act + Assert ///
        mockMvc.perform(get("/tags/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(TestData.TAG_NAME_1)));
        verify(tagService).getTag(id);
    }

    @Test
    void getCategory_withId_notFound() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        when(tagService.getTag(id)).thenReturn(Optional.empty());

        /// Act + Assert ///
        mockMvc.perform(get("/tags/" + id)).andExpect(status().isNotFound());
        verify(tagService).getTag(id);
    }
}
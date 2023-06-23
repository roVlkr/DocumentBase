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

import com.rovlkr.documentbase.mapping.TagMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.TagEntityBuilder;
import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.service.TagService;

@WebMvcTest(TagResource.class)
class TagResourceTest {

    @TestConfiguration
    static class TagResourceTestConfiguration {
        @Bean
        public TagMapper tagMapper() {
            return new TagMapper();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Test
    void createTag_defaultTagName_successful() throws Exception {
        /// Arrange ///
        TagEntity tagEntity = TagEntityBuilder.builder().id(null).name(TestData.TAG_NAME_1).build();
        when(tagService.createTag(tagEntity)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/tags").content(tagEntity.getName())) //
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(tagService).createTag(tagEntity);
    }

    @Test
    void getAllCategories_noArgs_successful() throws Exception {
        /// Arrange ///
        TagEntity tagEntity = TagEntityBuilder.builder().withDefaultValues1().build();
        when(tagService.getAllTags()).thenReturn(List.of(tagEntity));

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
        TagEntity tagEntity = TagEntityBuilder.builder().withDefaultValues1().build();
        when(tagService.getTag(id)).thenReturn(Optional.of(tagEntity));

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
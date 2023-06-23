package com.rovlkr.documentbase.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.DocumentEntityBuilder;
import com.rovlkr.documentbase.builder.model.NewDocumentBuilder;
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.mapping.CategoryMapper;
import com.rovlkr.documentbase.mapping.DocumentMapper;
import com.rovlkr.documentbase.mapping.TagMapper;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.service.DocumentService;

@WebMvcTest(DocumentResource.class)
class DocumentResourceTest {

    @TestConfiguration
    static class DocumentResourceTestConfiguration {
        @Bean
        public DocumentMapper documentMapper() {
            return new DocumentMapper(new CategoryMapper(), new TagMapper());
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocumentMapper mapper;

    @MockBean
    private DocumentService documentService;

    @Test
    void searchDocuments_withText_searchesViaService() throws Exception {
        /// Arrange ///
        final String searchText = "mySearchText";
        DocumentEntity documentEntity = DocumentEntityBuilder.builder().withDefaultValues().build();
        when(documentService.searchDocuments(eq(searchText), isNull(), isNull(), isNull(), isNull()))
                .thenReturn(Stream.of(documentEntity));

        /// Act + Assert ///
        mockMvc.perform(get("/documents").param("text", searchText)).andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$[0].name", equalTo(TestData.DOCUMENT_NAME))) //
                .andExpect(jsonPath("$[0].description", equalTo(TestData.DOCUMENT_DESCRIPTION)));
        verify(documentService).searchDocuments(eq(searchText), isNull(), isNull(), isNull(), isNull());
    }

    @Test
    void createDocument_defaultDocument_addsViaService() throws Exception {
        /// Arrange ///
        final NewDocument document = NewDocumentBuilder.builder().withDefaultValues().build();
        final DocumentEntity mappedEntity = mapper.toEntity(document);
        String json = new ObjectMapper().writeValueAsString(document);
        when(documentService.createDocument(mappedEntity)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/documents").content(json).contentType("application/json"))
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(documentService).createDocument(mappedEntity);
    }

    @Test
    void getDocument_withId_successful() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        DocumentEntity documentEntity = DocumentEntityBuilder.builder().withDefaultValues().build();
        when(documentService.getDocument(id)).thenReturn(Optional.of(documentEntity));

        /// Act + Assert ///
        mockMvc.perform(get("/documents/" + id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(TestData.DOCUMENT_NAME)));
        verify(documentService).getDocument(id);
    }

    @Test
    void getCategory_withId_notFound() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        when(documentService.getDocument(id)).thenReturn(Optional.empty());

        /// Act + Assert ///
        mockMvc.perform(get("/documents/" + id)).andExpect(status().isNotFound());
        verify(documentService).getDocument(id);
    }
}

package com.rovlkr.documentbase.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
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

import com.rovlkr.documentbase.dto.DocumentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.entity.Document;
import com.rovlkr.documentbase.mapping.DocumentMapper;
import com.rovlkr.documentbase.service.DocumentService;

@WebMvcTest(DocumentResource.class)
@Import(ResourceTestConfiguration.class)
class DocumentResourceTest {

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
        Document document = TestData.document().build();
        when(documentService.searchDocuments(eq(searchText), isNull(), isNull(), isNull(), isNull()))
                .thenReturn(Stream.of(document));

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
        final DocumentDTO documentDTO = TestData.newDocumentDTO().build();
        final Document document = TestData.newDocument().build();
        String json = new ObjectMapper().writeValueAsString(documentDTO);
        when(documentService.createDocument(any(Document.class))).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/documents").content(json).contentType("application/json"))
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(documentService).createDocument(document);
    }

    @Test
    void getDocument_withId_successful() throws Exception {
        /// Arrange ///
        final Long id = 1L;
        Document document = TestData.document().build();
        when(documentService.getDocument(id)).thenReturn(Optional.of(document));

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

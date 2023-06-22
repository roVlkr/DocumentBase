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

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.model.DocumentBuilder;
import com.rovlkr.documentbase.builder.model.NewDocumentBuilder;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.service.DocumentService;

@WebMvcTest(DocumentResource.class)
class DocumentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @Test
    void searchDocuments_withText_searchesViaService() throws Exception {
        /// Arrange ///
        final String searchText = "mySearchText";
        Document document = DocumentBuilder.builder().withDefaultValues().build();
        when(documentService.searchDocuments(eq(searchText), isNull(), isNull(), isNull(), isNull()))
                .thenReturn(Set.of(document));

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
        String json = new ObjectMapper().writeValueAsString(document);
        when(documentService.createDocument(document)).thenReturn(1L);

        /// Act + Assert ///
        mockMvc.perform(post("/documents").content(json).contentType("application/json"))
                .andExpect(status().isCreated()) //
                .andExpect(content().string("1"));
        verify(documentService).createDocument(document);
    }
}

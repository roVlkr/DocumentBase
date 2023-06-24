package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.rovlkr.documentbase.entity.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.repository.DocumentRepository;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Captor
    private ArgumentCaptor<Specification<Document>> specificationArg;

    @Mock
    private Page<Document> mockPage;

    @Mock
    private DocumentRepository repository;

    @InjectMocks
    private DocumentService classUnderTest;

    @Test
    void createDocument_withNewDocumentGiven_createsViaRepository() {
        /// Arrange ///
        Document document = TestData.newDocument().build();
        Document savedDocument = TestData.document().build();
        when(repository.save(document)).thenReturn(savedDocument);

        /// Act ///
        Long id = classUnderTest.createDocument(document);

        /// Assert ///
        verify(repository).save(document);
        assertThat(id).isEqualTo(TestData.DOCUMENT_ID);
    }

    @Test
    void searchDocuments_withTextGiven_searchesViaRepository() {
        /// Arrange ///
        final String searchText = "mySearchText";
        Document document = TestData.document().build();
        when(mockPage.get()).thenReturn(Stream.of(document));
        when(repository.findAll(specificationArg.capture(), any(Pageable.class))).thenReturn(mockPage);

        /// Act ///
        Stream<Document> foundDocuments = classUnderTest.searchDocuments(searchText, null, null, null, null);

        /// Assert ///
        Specification<Document> spec = specificationArg.getValue();
        assertThat(spec).isNotNull();
        verify(repository).findAll(eq(spec), any(Pageable.class));
        assertThat(foundDocuments).hasSize(1).first()
                .matches(d -> Objects.equals(document.getName(), d.getName()));
    }

    @Test
    void getDocument_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        Document document = TestData.document().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(document));

        /// Act ///
        Optional<Document> actual = classUnderTest.getDocument(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(document);
    }
}

package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

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
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.repository.DocumentRepository;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Captor
    private ArgumentCaptor<Specification<DocumentEntity>> specificationArg;

    @Mock
    private Page<DocumentEntity> mockPage;

    @Mock
    private DocumentRepository repository;

    @InjectMocks
    private DocumentService classUnderTest;

    @Test
    void createDocument_withNewDocumentGiven_createsViaRepository() {
        /// Arrange ///
        DocumentEntity documentEntity = TestData.newDocumentEntity().build();
        DocumentEntity savedEntity = TestData.documentEntity().build();
        when(repository.save(any(DocumentEntity.class))).thenReturn(savedEntity);

        /// Act ///
        Long id = classUnderTest.createDocument(documentEntity);

        /// Assert ///
        verify(repository).save(any(DocumentEntity.class));
        assertThat(id).isEqualTo(TestData.DOCUMENT_ID);
    }

    @Test
    void searchDocuments_withTextGiven_searchesViaRepository() {
        /// Arrange ///
        final String searchText = "mySearchText";
        DocumentEntity documentEntity = TestData.documentEntity().build();
        when(mockPage.get()).thenReturn(Stream.of(documentEntity));
        when(repository.findAll(specificationArg.capture(), any(Pageable.class))).thenReturn(mockPage);

        /// Act ///
        Stream<DocumentEntity> foundDocuments = classUnderTest.searchDocuments(searchText, null, null, null, null);

        /// Assert ///
        Specification<DocumentEntity> spec = specificationArg.getValue();
        assertThat(spec).isNotNull();
        verify(repository).findAll(eq(spec), any(Pageable.class));
        assertThat(foundDocuments).hasSize(1).first()
                .matches(document -> Objects.equals(documentEntity.getName(), document.getName()));
    }

    @Test
    void getDocument_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        DocumentEntity entity = TestData.documentEntity().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        /// Act ///
        Optional<DocumentEntity> actual = classUnderTest.getDocument(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(entity);
    }
}

package com.rovlkr.documentbase.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.DocumentEntityBuilder;
import com.rovlkr.documentbase.builder.model.DocumentBuilder;
import com.rovlkr.documentbase.builder.model.NewDocumentBuilder;
import com.rovlkr.documentbase.entity.CategoryEntity_;
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.entity.DocumentEntity_;
import com.rovlkr.documentbase.entity.TagEntity_;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;

@ExtendWith(MockitoExtension.class)
class DocumentMapperTest {

    @Spy
    private CategoryMapper categoryMapper = new CategoryMapper();

    @Spy
    private TagMapper tagMapper = new TagMapper();

    @InjectMocks
    private DocumentMapper classUnderTest;

    @Test
    void toEntity_withNewDocument_getsMapped() {
        /// Arrange ///
        NewDocument model = NewDocumentBuilder.builder().withDefaultValues().build();

        /// Act ///
        DocumentEntity actualEntity = classUnderTest.toEntity(model);

        /// Assert ///
        assertThat(actualEntity).hasFieldOrPropertyWithValue(DocumentEntity_.ID, null)
                .hasFieldOrPropertyWithValue(DocumentEntity_.NAME, TestData.DOCUMENT_NAME)
                .hasFieldOrPropertyWithValue(DocumentEntity_.DESCRIPTION, TestData.DOCUMENT_DESCRIPTION)
                .hasFieldOrPropertyWithValue(DocumentEntity_.FILE_LOCATION, model.getFileName())
                .hasFieldOrPropertyWithValue(DocumentEntity_.SENSIBLE, TestData.DOCUMENT_SENSIBILITY);
        assertThat(actualEntity.getCategory()).hasFieldOrPropertyWithValue(CategoryEntity_.ID, TestData.CATEGORY_ID);
        assertThat(actualEntity.getTags()).hasSize(2).element(1).hasFieldOrPropertyWithValue(TagEntity_.ID,
                TestData.TAG_ID_2);
    }

    @Test
    void toModel_withEntity_getsMapped() {
        /// Arrange ///
        DocumentEntity entity = DocumentEntityBuilder.builder().withDefaultValues().build();
        Document document = DocumentBuilder.builder().withDefaultValues().build();

        /// Act ///
        Document actualDocument = classUnderTest.toModel(entity);

        /// Assert ///
        assertThat(actualDocument).isEqualTo(document);
    }
}

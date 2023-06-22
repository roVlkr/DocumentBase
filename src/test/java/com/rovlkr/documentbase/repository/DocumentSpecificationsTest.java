package com.rovlkr.documentbase.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.jpa.domain.Specification.where;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.entity.TagEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DocumentSpecificationsTest {

    @Autowired
    private DocumentRepository repository;

    @Test
    @Sql("/sql/insertDocumentsWithCategories.sql")
    void documentHasCategory_documentWithAndWithoutCategoryGiven_findsTheFittingEntity() {
        /// Arrange ///
        final Long categoryId = 1L;
        final String categoryName = "Rechnung";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentHasCategory(categoryId)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first().matches(d -> categoryName.equals(d.getCategory().getName()));
    }

    @Test
    @Sql("/sql/insertDocumentsWithTags.sql")
    void documentContainsTags_noTag_findsAllEntities() {
        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentContainsTags(Collections.emptySet())));

        /// Assert ///
        assertThat(foundDocuments).hasSize(3);
    }

    @Test
    @Sql("/sql/insertDocumentsWithTags.sql")
    void documentContainsTags_oneSharedTag_findsTwoEntities() {
        /// Arrange ///
        final Long tagId = 1L;
        final String tagName1 = "krankenkasse";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentContainsTags(Collections.singleton(tagId))));

        /// Assert ///
        assertThat(foundDocuments).hasSize(2)
                .allSatisfy(d -> assertThat(d.getTags()).map(TagEntity::getName).contains(tagName1));
    }

    @Test
    @Sql("/sql/insertDocumentsWithTags.sql")
    void documentContainsTags_intersectingTwoTags_findsTheFittingEntity() {
        /// Arrange ///
        final Set<Long> allTags = Set.of(1L, 2L);
        final String tagName1 = "krankenkasse";
        final String tagName2 = "kfz-versicherung";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentContainsTags(allTags)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first()
                .satisfies(d -> assertThat(d.getTags()).map(TagEntity::getName).containsExactly(tagName1, tagName2));
    }

    @Test
    @Sql("/sql/insertDocumentsWithSensibility.sql")
    void documentHasSensibility_specificSensibility_findsOne() {
        /// Arrange ///
        final boolean sensible = true;

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentHasSensibility(sensible)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first().matches(DocumentEntity::getSensible);
    }

    @Test
    @Sql("/sql/insertDocumentsWithNamesAndDescription.sql")
    void documentMatchesText_searchText_findOneDocumentWithMatchingDescription() {
        /// Arrange ///
        final String textToSearchFor = "Schule";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentMatchesText(textToSearchFor)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first().matches(d -> d.getDescription().contains(textToSearchFor));
    }

    @Test
    @Sql("/sql/insertDocumentsWithNamesAndDescription.sql")
    void documentMatchesText_searchText_findOneDocumentWithMatchingName() {
        /// Arrange ///
        final String textToSearchFor = "Rechnung123";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentMatchesText(textToSearchFor)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first().matches(d -> d.getName().contains(textToSearchFor));
    }

    @Test
    @Sql("/sql/insertDocumentsWithNamesAndDescription.sql")
    void documentMatchesText_searchText_findAllDocumentWithMatchingName() {
        /// Arrange ///
        final String textToSearchFor = "123";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentMatchesText(textToSearchFor)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(2);
    }

    @Test
    @Sql("/sql/insertDocumentsWithNamesAndDescription.sql")
    void documentMatchesText_searchTextCaseInsensitive_findOneDocument() {
        /// Arrange ///
        final String textToSearchFor = "rechnung";

        /// Act ///
        List<DocumentEntity> foundDocuments =
                repository.findAll(where(DocumentSpecifications.documentMatchesText(textToSearchFor)));

        /// Assert ///
        assertThat(foundDocuments).hasSize(1).first()
                .satisfies(d -> assertThat(d.getName()).doesNotContain(textToSearchFor))
                .matches(d -> d.getName().toLowerCase().contains(textToSearchFor));
    }
}

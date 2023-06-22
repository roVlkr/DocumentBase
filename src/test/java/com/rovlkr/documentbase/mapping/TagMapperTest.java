package com.rovlkr.documentbase.mapping;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.builder.entity.TagEntityBuilder;
import com.rovlkr.documentbase.builder.model.TagBuilder;
import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.entity.TagEntity_;
import com.rovlkr.documentbase.model.Tag;

@ExtendWith(MockitoExtension.class)
class TagMapperTest {

    @InjectMocks
    private TagMapper classUnderTest;

    @Test
    void toEntity_withTagName_getsMapped() {
        /// Arrange ///
        String tagName = TestData.TAG_NAME_1;

        /// Act ///
        TagEntity actualEntity = classUnderTest.toEntity(tagName);

        /// Assert ///
        assertThat(actualEntity).hasFieldOrPropertyWithValue(TagEntity_.ID, null)
                .hasFieldOrPropertyWithValue(TagEntity_.NAME, TestData.TAG_NAME_1);
    }

    @Test
    void toEntity_withTag_getsMapped() {
        /// Arrange ///
        Tag tag = TagBuilder.builder().withDefaultValues1().build();
        TagEntity entity = TagEntityBuilder.builder().withDefaultValues1().build();

        /// Act ///
        TagEntity actualEntity = classUnderTest.toEntity(tag);

        /// Assert ///
        assertThat(actualEntity).isEqualTo(entity);
    }

    @Test
    void toModel_withEntity_getsMapped() {
        /// Arrange ///
        Tag tag = TagBuilder.builder().withDefaultValues1().build();
        TagEntity entity = TagEntityBuilder.builder().withDefaultValues1().build();

        /// Act ///
        Tag actualTag = classUnderTest.toModel(entity);

        /// Assert ///
        assertThat(actualTag).isEqualTo(tag);
    }
}

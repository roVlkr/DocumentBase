package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.repository.TagRepository;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    TagRepository repository;

    @InjectMocks
    private TagService classUnderTest;

    @Test
    void createTag_withTagName_createsViaRepository() {
        /// Arrange ///
        TagEntity tagEntity = TestData.newTagEntity().build();
        TagEntity savedEntity = TestData.tagEntity1().build();
        when(repository.save(tagEntity)).thenReturn(savedEntity);

        /// Act ///
        Long id = classUnderTest.createTag(tagEntity);

        /// Assert ///
        verify(repository).save(tagEntity);
        assertThat(id).isEqualTo(TestData.TAG_ID_1);
    }

    @Test
    void getAllTags_withoutArgs_getsAllViaRepository() {
        /// Arrange ///
        TagEntity entity = TestData.tagEntity1().build();
        when(repository.findAll()).thenReturn(List.of(entity));

        /// Act ///
        List<TagEntity> tags = classUnderTest.getAllTags();

        /// Assert ///
        verify(repository).findAll();
        assertThat(tags).hasSize(1).first().matches(tag -> Objects.equals(tag.getName(), entity.getName()));
    }

    @Test
    void getTag_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        TagEntity entity = TestData.tagEntity1().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(entity));

        /// Act ///
        Optional<TagEntity> actual = classUnderTest.getTag(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(entity);
    }
}

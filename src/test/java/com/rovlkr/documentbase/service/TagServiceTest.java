package com.rovlkr.documentbase.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.rovlkr.documentbase.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rovlkr.documentbase.TestData;
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
        Tag tag = TestData.newTag().build();
        Tag savedTag = TestData.tag1().build();
        when(repository.save(tag)).thenReturn(savedTag);

        /// Act ///
        Long id = classUnderTest.createTag(tag);

        /// Assert ///
        verify(repository).save(tag);
        assertThat(id).isEqualTo(TestData.TAG_ID_1);
    }

    @Test
    void getAllTags_withoutArgs_getsAllViaRepository() {
        /// Arrange ///
        Tag tag = TestData.tag1().build();
        when(repository.findAll()).thenReturn(List.of(tag));

        /// Act ///
        List<Tag> tags = classUnderTest.getAllTags();

        /// Assert ///
        verify(repository).findAll();
        assertThat(tags).hasSize(1).first().matches(t -> Objects.equals(t.getName(), tag.getName()));
    }

    @Test
    void getTag_withId_getsViaRepository() {
        /// Arrange ///
        Long id = 1L;
        Tag tag = TestData.tag1().id(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(tag));

        /// Act ///
        Optional<Tag> actual = classUnderTest.getTag(id);

        /// Assert ///
        verify(repository).findById(id);
        assertThat(actual).isPresent().get().isEqualTo(tag);
    }
}

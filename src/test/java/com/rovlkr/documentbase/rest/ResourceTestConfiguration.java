package com.rovlkr.documentbase.rest;

import com.rovlkr.documentbase.mapping.CategoryMapper;
import com.rovlkr.documentbase.mapping.DocumentMapper;
import com.rovlkr.documentbase.mapping.TagMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class ResourceTestConfiguration {

    @Bean
    public CategoryMapper categoryMapper() {
        return Mappers.getMapper(CategoryMapper.class);
    }

    @Bean
    public TagMapper tagMapper() {
        return Mappers.getMapper(TagMapper.class);
    }

    @Bean
    public DocumentMapper documentMapper() {
        return Mappers.getMapper(DocumentMapper.class);
    }
}

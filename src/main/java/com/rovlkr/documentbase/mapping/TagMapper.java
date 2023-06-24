package com.rovlkr.documentbase.mapping;


import org.mapstruct.Mapper;

import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toModel(TagEntity entity);

    TagEntity toEntity(Tag model);

    default TagEntity toEntity(String tagName) {
        return TagEntity.builder().name(tagName).build();
    }
}

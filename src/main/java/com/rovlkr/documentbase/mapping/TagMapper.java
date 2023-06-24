package com.rovlkr.documentbase.mapping;


import org.mapstruct.Mapper;

import com.rovlkr.documentbase.entity.Tag;
import com.rovlkr.documentbase.dto.TagDTO;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDTO toDTO(Tag entity);

    Tag toEntity(TagDTO dto);

    default Tag toEntity(String tagName) {
        return Tag.builder().name(tagName).build();
    }
}

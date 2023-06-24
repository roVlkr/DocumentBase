package com.rovlkr.documentbase.mapping;

import com.rovlkr.documentbase.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rovlkr.documentbase.dto.DocumentDTO;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class, TagMapper.class })
public interface DocumentMapper {

    /**
     * Read-Only access for Document-DTO.
     */
    @Mapping(target = "tag", ignore = true) // warning due to builder
    DocumentDTO toDTO(Document document);

    /**
     * Write-Only access for NewDocument-DTO.
     */
    @Mapping(target = "tag", ignore = true)
    Document toEntity(DocumentDTO dto);
}

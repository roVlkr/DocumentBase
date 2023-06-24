package com.rovlkr.documentbase.mapping;

import org.mapstruct.Mapper;

import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class, TagMapper.class })
public interface DocumentMapper {

    /**
     * Read-Only access for Document-DTO.
     */
    @Mapping(target = "tag", ignore = true) // warning due to builder
    Document toModel(DocumentEntity entity);

    /**
     * Write-Only access for NewDocument-DTO.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tag", ignore = true)
    @Mapping(target = "fileLocation", ignore = true)
    DocumentEntity toEntity(NewDocument model);
}

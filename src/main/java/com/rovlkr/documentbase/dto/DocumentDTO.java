package com.rovlkr.documentbase.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {

    private Long id;
    private String name;
    private String description;
    private CategoryDTO category;
    @Singular
    private Set<TagDTO> tags;
    private Boolean sensible;
    private String fileLocation;
}

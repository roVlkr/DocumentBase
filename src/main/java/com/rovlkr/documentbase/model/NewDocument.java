package com.rovlkr.documentbase.model;

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
public class NewDocument {

    private String name;
    private String description;
    private Category category;
    @Singular
    private Set<Tag> tags;
    private boolean sensible;
}

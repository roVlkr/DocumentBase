package com.rovlkr.documentbase.model;

import lombok.Data;

import java.util.Set;

@Data
public class Document {

    private long id;
    private String name;
    private String description;
    private Category category;
    private Set<Tag> tags;
    private boolean sensible;
    private String fileLocation;
}

package com.rovlkr.documentbase.model;

import java.util.Set;

import lombok.Data;

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

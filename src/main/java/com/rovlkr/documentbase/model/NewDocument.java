package com.rovlkr.documentbase.model;

import lombok.Data;

import java.util.List;

@Data
public class NewDocument {

    private String name;
    private String description;
    private Category category;
    private List<Tag> tags;
    private boolean sensible;
    private String fileName;
}

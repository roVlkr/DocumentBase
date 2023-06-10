package com.rovlkr.documentbase.mapping;


import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.model.Tag;

public class TagMapper {

    public Tag entityToModel(TagEntity entity) {
        Tag tag = new Tag();
        tag.setId(entity.getId());
        tag.setName(entity.getName());

        return tag;
    }

    public TagEntity modelToEntity(Tag model) {
        return new TagEntity(model.getId(), model.getName());
    }
}

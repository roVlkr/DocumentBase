package com.rovlkr.documentbase.mapping;


import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public Tag toModel(TagEntity entity) {
        Tag tag = new Tag();
        tag.setId(entity.getId());
        tag.setName(entity.getName());

        return tag;
    }

    public TagEntity toEntity(Tag model) {
        return new TagEntity(model.getId(), model.getName());
    }

    public TagEntity toEntity(String tagName) {
        return new TagEntity(tagName);
    }
}

package com.rovlkr.documentbase;

import com.rovlkr.documentbase.entity.CategoryEntity;
import com.rovlkr.documentbase.entity.DocumentEntity;
import com.rovlkr.documentbase.entity.TagEntity;
import com.rovlkr.documentbase.model.Category;
import com.rovlkr.documentbase.model.Document;
import com.rovlkr.documentbase.model.NewDocument;
import com.rovlkr.documentbase.model.Tag;

public interface TestData {

    Long DOCUMENT_ID = 437L;
    String DOCUMENT_NAME = "Rechnung Versicherung 2023";
    String DOCUMENT_DESCRIPTION = "Dies ist eine Beschreibung des Dokuments";
    boolean DOCUMENT_SENSIBILITY = false;
    String DOCUMENT_FILE_LOCATION = "/tmp/rechnung/rechnung23-2c8b6d8b-5582-4e5b-af3d-9b648e92b634.pdf";

    static Document.DocumentBuilder document() {
        return Document.builder().id(DOCUMENT_ID).name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY).fileLocation(DOCUMENT_FILE_LOCATION);
    }

    static NewDocument.NewDocumentBuilder newDocument() {
        return NewDocument.builder().name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY);
    }

    static DocumentEntity.DocumentEntityBuilder documentEntity() {
        return DocumentEntity.builder().id(DOCUMENT_ID).name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY).fileLocation(DOCUMENT_FILE_LOCATION);
    }

    static DocumentEntity.DocumentEntityBuilder newDocumentEntity() {
        return DocumentEntity.builder().name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY);
    }

    Long CATEGORY_ID = 591L;
    String CATEGORY_NAME = "Rechnung";

    static Category.CategoryBuilder category() {
        return Category.builder().id(CATEGORY_ID).name(CATEGORY_NAME);
    }

    static CategoryEntity.CategoryEntityBuilder categoryEntity() {
        return CategoryEntity.builder().id(CATEGORY_ID).name(CATEGORY_NAME);
    }

    static Category.CategoryBuilder newCategory() {
        return Category.builder().name(CATEGORY_NAME);
    }

    static CategoryEntity.CategoryEntityBuilder  newCategoryEntity() {
        return CategoryEntity.builder().name(CATEGORY_NAME);
    }

    Long TAG_ID_1 = 824L;
    String TAG_NAME_1 = "versicherung";
    Long TAG_ID_2 = 179L;
    String TAG_NAME_2 = "2023";

    static Tag.TagBuilder tag1() {
        return Tag.builder().id(TAG_ID_1).name(TAG_NAME_1);
    }

    static TagEntity.TagEntityBuilder tagEntity1() {
        return TagEntity.builder().id(TAG_ID_1).name(TAG_NAME_1);
    }

    static Tag.TagBuilder tag2() {
        return Tag.builder().id(TAG_ID_2).name(TAG_NAME_2);
    }

    static TagEntity.TagEntityBuilder tagEntity2() {
        return TagEntity.builder().id(TAG_ID_2).name(TAG_NAME_2);
    }

    static Tag.TagBuilder newTag() {
        return Tag.builder().name(TAG_NAME_1);
    }

    static TagEntity.TagEntityBuilder newTagEntity() {
        return TagEntity.builder().name(TAG_NAME_1);
    }
}

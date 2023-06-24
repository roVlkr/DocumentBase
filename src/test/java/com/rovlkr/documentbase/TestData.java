package com.rovlkr.documentbase;

import com.rovlkr.documentbase.entity.Category;
import com.rovlkr.documentbase.entity.Document;
import com.rovlkr.documentbase.entity.Tag;
import com.rovlkr.documentbase.dto.CategoryDTO;
import com.rovlkr.documentbase.dto.DocumentDTO;
import com.rovlkr.documentbase.dto.TagDTO;

public interface TestData {

    Long DOCUMENT_ID = 437L;
    String DOCUMENT_NAME = "Rechnung Versicherung 2023";
    String DOCUMENT_DESCRIPTION = "Dies ist eine Beschreibung des Dokuments";
    boolean DOCUMENT_SENSIBILITY = false;
    String DOCUMENT_FILE_LOCATION = "/tmp/rechnung/rechnung23-2c8b6d8b-5582-4e5b-af3d-9b648e92b634.pdf";

    static DocumentDTO.DocumentDTOBuilder documentDTO() {
        return DocumentDTO.builder().id(DOCUMENT_ID).name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY).fileLocation(DOCUMENT_FILE_LOCATION);
    }

    static DocumentDTO.DocumentDTOBuilder newDocumentDTO() {
        return DocumentDTO.builder().name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY);
    }

    static Document.DocumentBuilder document() {
        return Document.builder().id(DOCUMENT_ID).name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY).fileLocation(DOCUMENT_FILE_LOCATION);
    }

    static Document.DocumentBuilder newDocument() {
        return Document.builder().name(DOCUMENT_NAME).description(DOCUMENT_DESCRIPTION)
                .sensible(DOCUMENT_SENSIBILITY);
    }

    Long CATEGORY_ID = 591L;
    String CATEGORY_NAME = "Rechnung";

    static CategoryDTO.CategoryDTOBuilder categoryDTO() {
        return CategoryDTO.builder().id(CATEGORY_ID).name(CATEGORY_NAME);
    }

    static Category.CategoryBuilder category() {
        return Category.builder().id(CATEGORY_ID).name(CATEGORY_NAME);
    }

    static CategoryDTO.CategoryDTOBuilder newCategoryDTO() {
        return CategoryDTO.builder().name(CATEGORY_NAME);
    }

    static Category.CategoryBuilder newCategory() {
        return Category.builder().name(CATEGORY_NAME);
    }

    Long TAG_ID_1 = 824L;
    String TAG_NAME_1 = "versicherung";
    Long TAG_ID_2 = 179L;
    String TAG_NAME_2 = "2023";

    static TagDTO.TagDTOBuilder tagDTO1() {
        return TagDTO.builder().id(TAG_ID_1).name(TAG_NAME_1);
    }

    static Tag.TagBuilder tag1() {
        return Tag.builder().id(TAG_ID_1).name(TAG_NAME_1);
    }

    static TagDTO.TagDTOBuilder tagDTO2() {
        return TagDTO.builder().id(TAG_ID_2).name(TAG_NAME_2);
    }

    static Tag.TagBuilder tag2() {
        return Tag.builder().id(TAG_ID_2).name(TAG_NAME_2);
    }

    static TagDTO.TagDTOBuilder newTagDTO() {
        return TagDTO.builder().name(TAG_NAME_1);
    }

    static Tag.TagBuilder newTag() {
        return Tag.builder().name(TAG_NAME_1);
    }
}

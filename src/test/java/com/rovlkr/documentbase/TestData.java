package com.rovlkr.documentbase;

public interface TestData {

    Long DOCUMENT_ID = 437L;
    String DOCUMENT_NAME = "Rechnung Versicherung 2023";
    String DOCUMENT_DESCRIPTION = "Dies ist eine Beschreibung des Dokuments";
    boolean DOCUMENT_SENSIBILITY = false;
    String DOCUMENT_FILE_NAME = "rechnung23.pdf";
    String DOCUMENT_FILE_LOCATION = "/tmp/rechnung/rechnung23-2c8b6d8b-5582-4e5b-af3d-9b648e92b634.pdf";

    Long CATEGORY_ID = 591L;
    String CATEGORY_NAME = "Rechnung";

    Long TAG_ID_1 = 824L;
    String TAG_NAME_1 = "versicherung";
    Long TAG_ID_2 = 179L;
    String TAG_NAME_2 = "2023";
}

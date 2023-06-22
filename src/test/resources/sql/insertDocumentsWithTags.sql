INSERT INTO tag (id, name) VALUES (1, 'krankenkasse');
INSERT INTO tag (id, name) VALUES (2, 'kfz-versicherung');

INSERT INTO document (id) VALUES (1);
INSERT INTO document (id) VALUES (2);
INSERT INTO document (id) VALUES (3);

-- first and document get only one tag
INSERT INTO document_tags (document_id, tag_id) VALUES (1, 1);
INSERT INTO document_tags (document_id, tag_id) VALUES (2, 2);

-- third document gets all tags
INSERT INTO document_tags (document_id, tag_id) VALUES (3, 1);
INSERT INTO document_tags (document_id, tag_id) VALUES (3, 2);

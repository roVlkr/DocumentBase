INSERT INTO category (id, name) VALUES (1, 'Rechnung');
INSERT INTO category (id, name) VALUES (2, 'Mahnung');

INSERT INTO document (category_id) VALUES (1);
INSERT INTO document (category_id) VALUES (2);
INSERT INTO category_entity (name) VALUES ('Food');
INSERT INTO category_entity (name) VALUES ('Sweets');

INSERT INTO list_entity (name, description) VALUES ('Na jutro', 'we idz do lidla bo tam promka na kurczaka jest wariatku');
INSERT INTO list_entity (name) VALUES ('Na weekend');

INSERT INTO item_entity (name, price, amount, category_id, list_id, weight, time_created)
VALUES (
        'Bread',
        3.20,
        1,
        1,
        1,
        0,
        '2023-11-05 20:13:20'
       );

INSERT INTO item_entity (name, price, amount, category_id, list_id, weight, time_created)
VALUES (
        'Milk',
        3.20,
        1,
        1,
        1,
        0,
        '2023-11-10 20:13:20'
       );

INSERT INTO item_entity (name, price, amount, category_id, list_id, weight, time_created)
VALUES (
        'Potato',
        3.20,
        10,
        1,
        1,
        750,
        '2023-08-07 20:13:20'
       );

INSERT INTO item_entity (name, price, amount, category_id, list_id, weight, time_created)
VALUES (
        'Carrot',
        3.20,
        3,
        1,
        2,
        500,
        '2023-11-11 20:13:20'
       );

INSERT INTO item_entity (name, price, amount, category_id, list_id, weight, time_created)
VALUES (
        'Cola',
        3.20,
        1,
        2,
        2,
        0,
        '2023-09-11 20:13:20'
       );
INSERT INTO item_entity (name, price, category_id, list_id, time_created)
VALUES (
           'Cola',
           3.20,
           2,
           2,
           '2023-08-11 20:13:20'
       );
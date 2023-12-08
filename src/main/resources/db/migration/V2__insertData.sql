INSERT INTO category_entity (name) VALUES ('Food');
INSERT INTO category_entity (name) VALUES ('Sweets');
INSERT INTO category_entity (name) VALUES ('Cosmetics and Hygiene');

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

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, amount, time_created, time_bought)
VALUES (
            100,
            10,
            1,
            'Skyr',
            2.94,
            6,
            '2023-07-12 20:13:20',
            '2023-08-12 21:45:20'
       );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        101,
        10,
        1,
        'Woda',
        15.54,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        102,
        10,
        3,
        'Pasta do zębow',
        13.99,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        103,
        10,
        3,
        'Zel do ciala',
        13.99,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, weight, price, time_created, time_bought)
VALUES (
    104,
    10,
    1,
    'Banany',
    1380,
    9.65,
    '2023-07-12 20:13:20',
    '2023-08-12 21:45:20'
    );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        105,
        10,
        1,
        'Kielbasa',
        10.99,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, weight, price, time_created, time_bought)
VALUES (
        105,
        10,
        1,
        'Maliny mrozone',
        500,
        17.89,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        106,
        10,
        2,
        'Czekolada',
        13.99,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        107,
        10,
        1,
        'Ryz',
        6.49,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        108,
        10,
        1,
        'Platki owsiane',
        8.69,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, price, time_created, time_bought)
VALUES (
        109,
        10,
        1,
        'Sos do ryzu',
        7.99,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, weight, price, time_created, time_bought)
VALUES (
        110,
        10,
        1,
        'Kabanosy',
        300,
        12.94,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );

INSERT INTO purchased_item_entity(item_id, list_id, category_id, name, weight, price, time_created, time_bought)
VALUES (
        111,
        10,
        1,
        'Filet z piersi z kurczaka',
        574,
        12.59,
        '2023-07-12 20:13:20',
        '2023-08-12 21:45:20'
        );
CREATE TABLE IF NOT EXISTS category_entity (
    category_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS list_entity (
    list_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(300) DEFAULT ''
    );

CREATE TABLE IF NOT EXISTS item_entity (
    item_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    list_id BIGINT NOT NULL,
    name VARCHAR(20) NOT NULL,
    price DECIMAL(10, 2) NOT NULL default 0,
    amount INT NOT NULL default 1,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_entity (category_id),
    FOREIGN KEY (list_id) REFERENCES list_entity (list_id),
    weight BIGINT NOT NULL DEFAULT 0,
    time_created TIMESTAMP WITHOUT TIME ZONE
    );

CREATE TABLE IF NOT EXISTS purchased_item_entity (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    list_id BIGINT NOT NULL,
    category_id BIGINT,
    name VARCHAR(30) NOT NULL,
    price DECIMAL(10, 2) NOT NULL default 0,
    amount BIGINT NOT NULL default 1,
    weight BIGINT NOT NULL default 0,
    time_created TIMESTAMP WITHOUT TIME ZONE,
    time_bought TIMESTAMP WITHOUT TIME ZONE
    );

CREATE TABLE IF NOT EXISTS user_entity (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
    );
CREATE TABLE IF NOT EXISTS category_entity (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS list_entity (
    list_id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(300) NOT NULL DEFAULT ''
    );

CREATE TABLE IF NOT EXISTS item_entity (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL default 0,
    amount INT NOT NULL default 1,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_entity (category_id),
    list_id BIGINT,
    FOREIGN KEY (list_id) REFERENCES list_entity (list_id),
    weight BIGINT NOT NULL DEFAULT 0,
    time_created TIMESTAMP WITHOUT TIME ZONE
    );

CREATE TABLE IF NOT EXISTS user_item_entity (
    id SERIAL PRIMARY KEY,
    item_id BIGINT NOT NULL,
    list_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    weight BIGINT NOT NULL,
    time_created TIMESTAMP WITHOUT TIME ZONE,
    time_bought TIMESTAMP WITHOUT TIME ZONE
    );
CREATE TABLE IF NOT EXISTS user_entity (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    time_created TIMESTAMP WITHOUT TIME ZONE,
    role VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS category_entity (
    category_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (user_id),
    name VARCHAR(25) NOT NULL
    );

CREATE TABLE IF NOT EXISTS list_entity (
    list_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (user_id),
    name VARCHAR(30) NOT NULL,
    description VARCHAR(300) DEFAULT ''
    );

CREATE TABLE IF NOT EXISTS item_entity (
    item_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (user_id),
    list_id BIGINT NOT NULL,
    FOREIGN KEY (list_id) REFERENCES list_entity (list_id),
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_entity (category_id),
    name VARCHAR(20) NOT NULL,
    price DECIMAL(10, 2) NOT NULL DEFAULT 0,
    amount INT NOT NULL DEFAULT 1,
    weight BIGINT NOT NULL DEFAULT 0,
    time_created TIMESTAMP WITHOUT TIME ZONE
    );


CREATE TABLE IF NOT EXISTS purchased_item_entity (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_entity (user_id),
    list_id BIGINT NOT NULL,
    FOREIGN KEY (list_id) REFERENCES list_entity (list_id),
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category_entity (category_id),
    name VARCHAR(30) NOT NULL,
    price DECIMAL(10, 2) NOT NULL default 0,
    amount BIGINT NOT NULL default 1,
    weight BIGINT NOT NULL default 0,
    time_created TIMESTAMP WITHOUT TIME ZONE,
    time_bought TIMESTAMP WITHOUT TIME ZONE
    );

CREATE TABLE IF NOT EXISTS reset_password_token_entity (
    id SERIAL PRIMARY KEY,
    token VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    expiration_date TIMESTAMP WITHOUT TIME ZONE
    );
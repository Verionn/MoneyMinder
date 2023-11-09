CREATE TABLE IF NOT EXISTS list_entity (
    list_id SERIAL PRIMARY KEY,
    name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS item_entity (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    amount INT,
    category VARCHAR(255),
    list_id BIGINT,
    FOREIGN KEY (list_id) REFERENCES list_entity (list_id)
    );
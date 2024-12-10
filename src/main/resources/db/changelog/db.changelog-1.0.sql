--liquibase formatted sql

--changeset ryabchikov:1
CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE car_showrooms
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE cars
(
    id              SERIAL PRIMARY KEY,
    model           VARCHAR(50),
    brand           VARCHAR(50),
    production_date DATE,
    price           DECIMAL,
    category_id     BIGINT NOT NULL,
    car_showroom_id BIGINT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    CONSTRAINT fk_car_showroom FOREIGN KEY (car_showroom_id) REFERENCES car_showrooms (id) ON DELETE SET NULL
);

CREATE TABLE clients
(
    id                   SERIAL PRIMARY KEY,
    name                 VARCHAR(100) NOT NULL,
    date_of_registration DATE         NOT NULL
);

CREATE TABLE contacts
(
    client_id BIGINT,
    contact   VARCHAR(100),
    PRIMARY KEY (client_id, contact),
    CONSTRAINT fk_contact_client FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);

CREATE TABLE cars_clients
(
    car_id    BIGINT,
    client_id BIGINT,
    PRIMARY KEY (car_id, client_id),
    CONSTRAINT fk_car_client_car FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE,
    CONSTRAINT fk_car_client_client FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);

CREATE TABLE reviews
(
    id        SERIAL PRIMARY KEY,
    text      TEXT   NOT NULL,
    rating    INT    NOT NULL CHECK (rating BETWEEN 1 AND 5),
    car_id    BIGINT NOT NULL,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_review_car FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE,
    CONSTRAINT fk_review_client FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE
);
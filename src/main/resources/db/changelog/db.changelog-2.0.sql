--liquibase formatted sql

--changeset ryabchikov:1
INSERT INTO categories (name)
VALUES ('Sedan'),
       ('SUV'),
       ('Hatchback');

INSERT INTO car_showrooms (title, address)
VALUES ('City Auto', '123 Main St, New York'),
       ('Luxury Cars', '456 Beverly Hills, Los Angeles');

INSERT INTO clients (name, date_of_registration)
VALUES ('John Doe', '2023-01-15'),
       ('Jane Smith', '2023-03-20');

INSERT INTO contacts (client_id, contact)
VALUES (1, 'john.doe@example.com'),
       (1, '555-1234'),
       (2, 'jane.smith@example.com'),
       (2, '555-5678');

INSERT INTO cars (model, brand, production_date, price, category_id, car_showroom_id)
VALUES ('Civic', 'Honda', '2020-01-01', 22000.00, 1, 1),
       ('CR-V', 'Honda', '2021-01-01', 35000.50, 2, 2),
       ('Model 3', 'Tesla', '2022-01-01', 45000.99, 1, 2);

INSERT INTO cars_clients (car_id, client_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO reviews (text, rating, car_id, client_id)
VALUES ('Great car with excellent performance!', 5, 1, 1),
       ('Comfortable and fuel-efficient.', 4, 2, 2);
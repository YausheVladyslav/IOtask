CREATE TABLE users
(
    id           BIGSERIAL,
    PRIMARY KEY (id),
    name         VARCHAR(32),
    surname      VARCHAR(32),
    nickname     VARCHAR(32),
    phone_number VARCHAR(32)
);
/*
DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);*/
CREATE TABLE info  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    str_info VARCHAR(1500)
);
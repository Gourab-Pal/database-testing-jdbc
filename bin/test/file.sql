USE automationtesting;

CREATE TABLE users (
    userid INT PRIMARY KEY NOT NULL,
    username VARCHAR(50),
    city VARCHAR(50),
    salary INT
);

DELETE FROM users WHERE userid IS NULL;

SELECT * FROM users;

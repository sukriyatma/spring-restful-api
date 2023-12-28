
CREATE TABLE users
(
    username         VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(100) NOT NULL,
    token            VARCHAR(1000),
    token_refresh    VARCHAR(1000),
    PRIMARY KEY (username),
    UNIQUE (token)
);

SELECT * FROM users;
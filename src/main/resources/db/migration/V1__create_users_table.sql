CREATE TABLE users (
    id         CHAR(36)     NOT NULL,
    name       VARCHAR(255),
    email      VARCHAR(255) NOT NULL,
    created_at DATETIME(6)  NOT NULL,
    updated_at DATETIME(6),
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uq_users_email UNIQUE (email)
);

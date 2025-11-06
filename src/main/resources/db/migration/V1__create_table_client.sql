CREATE TABLE client (
    id_client UUID PRIMARY KEY NOT NULL,
    login_client VARCHAR(255) UNIQUE NOT NULL,
    password_client VARCHAR(255) NOT NULL
);
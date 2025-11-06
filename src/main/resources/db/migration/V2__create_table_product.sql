CREATE TABLE product (
    id_product UUID PRIMARY KEY NOT NULL,
    name_product VARCHAR(255) NOT NULL,
    description_product TEXT,
    price_product DOUBLE PRECISION NOT NULL,
    client_id UUID,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id_client)
);

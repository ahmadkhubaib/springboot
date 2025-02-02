DROP TABLE IF EXISTS
    drink_order_line;
DROP TABLE IF EXISTS
    drink_order;
CREATE TABLE drink_order(
        id VARCHAR(36) NOT NULL,
        created_date DATETIME(6) DEFAULT NULL,
        customer_ref VARCHAR(255) DEFAULT NULL,
        last_modified_date DATETIME(6) DEFAULT NULL,
        VERSION BIGINT DEFAULT NULL,
        customer_id VARCHAR(36) DEFAULT NULL,
        PRIMARY KEY(id),
        CONSTRAINT drink_order_fk_1 FOREIGN KEY(customer_id) REFERENCES customer(id)
);
CREATE TABLE drink_order_line(
        id VARCHAR(36) NOT NULL,
        created_date DATETIME(6) DEFAULT NULL,
        last_modified_date DATETIME(6) DEFAULT NULL,
        order_quantity INT DEFAULT NULL,
        quantity_allocated INT DEFAULT NULL,
        VERSION BIGINT DEFAULT NULL,
        drink_id VARCHAR(36) DEFAULT NULL,
        drink_order_id VARCHAR(36) DEFAULT NULL,
        PRIMARY KEY(id),
        CONSTRAINT drink_order_line_fk_1 FOREIGN KEY(drink_id) REFERENCES drink(id),
        CONSTRAINT drink_order_line_fk_2 FOREIGN KEY(drink_order_id) REFERENCES drink_order(id)
);

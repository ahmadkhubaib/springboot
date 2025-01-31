drop table if exists drink_order_line;
drop table if exists drink_order;

CREATE TABLE `drink_order` (
    id varchar(36) NOT NULL,
    created_date datetime(6) DEFAULT NULL,
    customer_ref varchar(255) DEFAULT NULL,
    last_modified_date datetime(6) DEFAULT NULL,
    version bigint DEFAULT NULL,
    customer_id varchar(36) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE `drink_order_line`
(
    id varchar(36) NOT NULL,
    created_date datetime(6) DEFAULT NULL,
    last_modified_date datetime(6) DEFAULT NULL,
    order_quantity int DEFAULT NULL,
    quantity_allocated int DEFAULT NULL,
    version bigint DEFAULT NULL,
    drink_id varchar(36) DEFAULT NULL,
    drink_order_id varchar(36) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (drink_id) REFERENCES drink (id),
    CONSTRAINT FOREIGN KEY (drink_order_id) REFERENCES drink_order (id)
);
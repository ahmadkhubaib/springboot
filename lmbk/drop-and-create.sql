drop table if exists customer cascade ;
drop table if exists drink cascade ;
create table customer (version integer, created_date timestamp(6), last_modified_date timestamp(6), id varchar(36) not null, customer_name varchar(50) not null, primary key (id));
create table drink (drink_style tinyint not null check (drink_style between 0 and 3), price numeric(38,2) not null, quantity_on_hand integer, version integer, created_date timestamp(6), updated_date timestamp(6), id varchar(36) not null, drink_name varchar(50) not null, upc varchar(255) not null, primary key (id));
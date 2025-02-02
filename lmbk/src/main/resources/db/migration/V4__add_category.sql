drop table if exists category;
drop table if exists drink_category;

create table category
(
    id varchar(36) not null primary key,
    description varchar(100),
    created_date timestamp,
    last_modified_date datetime(6) default null,
    version bigint null
);

create table drink_category
(
    drink_id varchar(36) not null,
    category_id varchar(36) not null,
    primary key(drink_id, category_id),
    constraint drink_id_fk foreign key(drink_id) references drink(id),
    constraint category_id_fk foreign key (category_id) references category(id)
);
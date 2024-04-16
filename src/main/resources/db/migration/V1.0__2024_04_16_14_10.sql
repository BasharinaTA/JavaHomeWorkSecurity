create table users
(
    id       serial
        constraint users_pk
            primary key,
    username varchar(20)                                     not null
        unique,
    password varchar(100)                                    not null,
    role     varchar(20) default 'USER'::character varying   not null,
    status   varchar(10) default 'ACTIVE'::character varying not null
);

create table pizzas
(
    id          serial
        constraint pizza_pk
            primary key,
    name        varchar(50)  not null,
    composition varchar(500) not null
);
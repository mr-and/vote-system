CREATE SEQUENCE GLOBAL_SEQ START WITH 1000 INCREMENT BY 1;

CREATE TABLE dishes
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    varchar(255) NOT NULL,
    price   bigint       NOT NULL,
    menu_id INTEGER      NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date          date    NOT NULL,
    restaurant_id integer NOT NULL
);

CREATE TABLE restaurants
(
    id   INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    varchar(255)
);

CREATE TABLE users
(
    id       INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    email    varchar(100) NOT NULL,
    name     varchar(255) NOT NULL,
    password varchar(100) NOT NULL
);

CREATE TABLE votes
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date    date    NOT NULL,
    restaurant_id INTEGER NOT NULL,
    user_id integer NOT NULL

);

ALTER TABLE users
    add constraint users_unique_email_idx unique (email);

ALTER TABLE dishes
    add constraint FKmjyxiavmsmhlx7p6xg46a1lkf
        foreign key (menu_id)
            references menu;

ALTER TABLE menu
    add constraint FKrr0ffv1553rcdbwsr7vcckipf
        foreign key (restaurant_id)
            references restaurants;

ALTER TABLE user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id)
            references users;


ALTER TABLE votes
    add constraint FKli4uj3ic2vypf5pialchj925e
        foreign key (user_id)
            references users
            on delete cascade;

ALTER TABLE votes
    add constraint FK93nqd6kky7cyvbe4q1eup9gcx
        foreign key (restaurant_id)
            references restaurants
            on delete cascade;


-- create sequence global_seq start with 1000 increment by 1;
--
-- create table dishes (
--                         id integer not null,
--                         name varchar(255) not null,
--                         price bigint not null check (price>=1),
--                         menu_id integer not null,
--                         primary key (id)
-- );
--
-- create table menu (
--                       id integer not null,
--                       date date not null,
--                       restaurant_id integer not null,
--                       primary key (id)
-- );
--
-- create table restaurants (
--                              id integer not null,
--                              name varchar(20) not null,
--                              primary key (id)
-- );
--
-- create table user_roles (
--                             user_id integer not null,
--                             role varchar(255)
-- );
--
-- create table users (
--                        id INTEGER DEFAULT GLOBAL_SEQ.nextval not null,
--                        email varchar(50) not null,
--                        name varchar(255) not null,
--                        password varchar(100) not null,
--                        primary key (id)
-- );
--
-- create table votes (
--                        id integer not null,
--                        date date not null,
--                        restaurant_id integer not null,
--                        user_id integer not null,
--                        primary key (id)
-- );
--
-- alter table dishes
--     add constraint dish_unique_name_idx unique (name);
--
-- alter table restaurants
--     add constraint name_address_unique_idx unique (name);
--
-- alter table user_roles
--     add constraint user_roles_unique_idx unique (user_id, role);
--
-- alter table users
--     add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
--
-- alter table users
--     add constraint UK_r53o2ojjw4fikudfnsuuga336 unique (password);
--
-- alter table dishes
--     add constraint FKmjyxiavmsmhlx7p6xg46a1lkf
--         foreign key (menu_id)
--             references menu;
--
-- alter table menu
--     add constraint FKrr0ffv1553rcdbwsr7vcckipf
--         foreign key (restaurant_id)
--             references restaurants
--             on delete cascade;
--
-- alter table user_roles
--     add constraint FKhfh9dx7w3ubf1co1vdev94g3f
--         foreign key (user_id)
--             references users;
--
-- alter table votes
--     add constraint FK93nqd6kky7cyvbe4q1eup9gcx
--         foreign key (restaurant_id)
--             references restaurants;
--
-- alter table votes
--     add constraint FKli4uj3ic2vypf5pialchj925e
--         foreign key (user_id)
--             references users;

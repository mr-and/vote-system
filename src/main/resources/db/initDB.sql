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
    menu_id integer NOT NULL,
    user_id integer NOT NULL
);

ALTER TABLE users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

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
    add constraint FKk9pfsvs7b6my3mmk7db9nafq4
        foreign key (menu_id)
            references menu
            on delete cascade;

ALTER TABLE votes
    add constraint FKli4uj3ic2vypf5pialchj925e
        foreign key (user_id)
            references users
            on delete cascade;
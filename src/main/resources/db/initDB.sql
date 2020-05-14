CREATE SEQUENCE GLOBAL_SEQ START WITH 1000 INCREMENT BY 1;

CREATE TABLE users
(
    id       INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    email    VARCHAR(100) UNIQUE NOT NULL,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(100) NOT NULL,
    constraint users_unique_email_idx unique (email)
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    foreign key (user_id) references users(id)
);

CREATE TABLE restaurants
(
    id   INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name VARCHAR(255)       NOT NULL
);

CREATE TABLE menu
(
    id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date          date      NOT NULL,
    restaurant_id integer   NOT NULL,
    foreign key (restaurant_id) references restaurants(id) on delete cascade
);

CREATE TABLE dishes
(
    id      INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    name    VARCHAR(255)    NOT NULL,
    price   BIGINT          NOT NULL,
    menu_id INTEGER         NOT NULL,
    foreign key (menu_id)   references menu(id) on delete cascade
);

CREATE TABLE votes
(
    id              INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
    date            DATE    NOT NULL,
    restaurant_id   INTEGER,
    user_id         INTEGER NOT NULL,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (restaurant_id) references restaurants(id) on delete SET NULL
);
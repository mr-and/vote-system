ALTER SEQUENCE GLOBAL_SEQ restart with 1000;

INSERT INTO users (name, email, password)
VALUES ('User One', 'user.one@mail.ru', '{noop}password'),
       ('User Two', 'user.two@yandex.ru', '{noop}password'),
       ('Admin One', 'admin.one@gmail.com', '{noop}admin'),
       ('Admin Two', 'admin.two@gmail.com', '{noop}admin');
--ID 1000/1003

INSERT INTO restaurants (name)
VALUES ('Евразия'),
       ('Невский'),
       ('Питерский'),
       ('Ленинградский');
--ID 1004/1007

INSERT INTO menu (date, restaurant_id)
VALUES ('2019-06-01', 1004),
       ('2019-06-01', 1004),
       ('2019-06-01', 1005),
       ('2019-06-01', 1005),
       ('2019-06-01', 1006),
       ('2019-06-01', 1006),
       ('2019-06-01', 1007),
       ('2019-06-01', 1007);
--ID     1008/1015

INSERT INTO dishes (name, price, menu_id)
VALUES ('Мясо', 100, 1008),
       ('Рыба', 100, 1009),
       ('Салат', 100, 1010),
       ('Картофель', 100, 1011),
       ('Овощи', 100, 1012),
       ('Фрукты', 100, 1013);


INSERT INTO votes (date, menu_id, user_id)
VALUES ('2020-05-05', 1008, 1000);
--ID 1024

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1000),
       ('ROLE_USER', 1001),
       ('ROLE_ADMIN', 1002),
       ('ROLE_ADMIN', 1003);

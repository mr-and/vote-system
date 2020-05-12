ALTER SEQUENCE GLOBAL_SEQ restart with 1000;

INSERT INTO users (name, email, password)
VALUES ('User One', 'user.one@mail.ru', 'password'),
       ('User Two', 'user.two@yandex.ru', 'password'),
       ('Admin One', 'admin.one@gmail.com', 'admin'),
       ('Admin Two', 'admin.two@gmail.com', 'admin');
--ID 1000/1003

INSERT INTO restaurants (name)
VALUES ('Евразия'),
       ('Невский'),
       ('Питерский'),
       ('Ленинградский');
--ID 1004/1007

INSERT INTO menu (date, restaurant_id)
VALUES ('2020-05-01', 1004),
       ('2020-05-01', 1004),
       ('2020-05-01', 1005),
       ('2020-05-01', 1005),
       ('2020-05-01', 1006),
       ('2020-05-01', 1006),
       ('2020-05-01', 1007),
       ('2020-05-01', 1007);
--ID     1008/1015

INSERT INTO dishes (name, price, menu_id)
VALUES ('Мясо', 100, 1008),
       ('Рыба', 100, 1009),
       ('Салат', 100, 1010),
       ('Картофель', 100, 1011),
       ('Овощи', 100, 1012),
       ('Фрукты', 100, 1013);
--ID     1015/1021

INSERT INTO votes (date, restaurant_id, user_id)
VALUES ('2020-05-01', 1004, 1000);
VALUES ('2020-05-01', 1004, 1001);
VALUES ('2020-05-01', 1004, 1002);
VALUES ('2020-05-01', 1004, 1003);
--ID 1022

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 1000),
       ('ROLE_USER', 1001),
       ('ROLE_ADMIN', 1002),
       ('ROLE_ADMIN', 1003);


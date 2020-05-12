#### Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

1. Two types of users: admin and regular users;
2. Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price);
3. Menu changes each day (admins do the updates);
4. Users can vote on which restaurant they want to have lunch at;
5. Only one vote counted per user;
6. If user votes again the same day:

    * If it is before 11:00 we asume that he changed his mind
    
    * If it is after 11:00 then it is too late, vote can't be changed
 
7. Each restaurant provides new menu each day.

###### As a result, provide a link to github voteRepository.

###### It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

######P.S.: Make sure everything works with latest version that is on github :)

------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/                            получить все рестораны +

  curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/{id}                        получить ресторан по id +

  curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/                            добавить ресторан(только админ) +

  curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants -H 'Content-Type: application/json' -d '{"name":"New", "votes":null}'
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/{id}/menu                   добавить меню ресторану(только админ) +

  curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants/1004/menu -H 'Content-Type: application/json' -d '{"date":"2020-05-02"}'
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/{id}/menu                   получить все меню конкретного ресторана  +

  curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004/menu
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/{id}/menu/{id}              получить конкретное меню с едой конкретного ресторана +

  curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004/menu/1008 
------------------------------------------------------------------------------------------------------------------------
- PUT       /api/v1/restaurants/{id}/menu/{id}              обновить меню конкретного ресторана(только админ) +

  curl -s -u admin.one@gmail.com:admin -X PUT http://localhost:8080/api/v1/restaurants/1004/menu/1008 -H 'Content-Type: application/json' -d '{"date":"2020-06-05"}' 
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/{id}/menu/{id}/dish         добавить еду в меню конкретного ресторана(только админ)

  curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants/1004/menu/1008/dish -H 'Content-Type: application/json' -d '{"name":"New","price":40}'      
------------------------------------------------------------------------------------------------------------------------
- PUT       /api/v1/restaurants/{id}/menu/{id}/dish{id}     обновить еду для меню конкретного ресторана(только админ)

  curl -s -u admin.one@gmail.com:admin -X PUT http://localhost:8080/api/v1/restaurants/1004/menu/1008/dish/1016 -H 'Content-Type: application/json' -d '{"name":"NewTwo","price":30}'
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/{id}/menu/{id}/votes        добавить голос за меню ресторана

  curl -s -u user.one@mail.ru:password -X POST http://localhost:8080/api/v1/restaurants/1004/votes -H 'Content-Type: application/json' -d '{"date":"2020-06-05"}' 
------------------------------------------------------------------------------------------------------------------------
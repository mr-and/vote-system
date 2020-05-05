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

###### As a result, provide a link to github repository.

###### It should contain the code and README.md with API documentation and curl commands to get data for voting and vote.

######P.S.: Make sure everything works with latest version that is on github :)

------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/                        получить все рестораны
    curl -s http://localhost:8080/api/v1/restaurants
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/                        получить ресторан по id
    curl -s http://localhost:8080/api/v1/restaurants/1004
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/                        добавить ресторан(только админ)
    curl -s -X POST http://localhost:8080/api/v1/restaurants -H 'Content-Type: application/json' -d '{"name":"Шушары"}'
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/menu?date=?             получить все меню всех ресторанов по дате
------------------------------------------------------------------------------------------------------------------------
- GET       /api/v1/restaurants/{id}/menu?date=?        получить меню конкретного ресторана
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/{id}/menu/              добавить меню конкретного ресторана(только админ)
------------------------------------------------------------------------------------------------------------------------
- PUT       /api/v1/restaurants/{id}/menu/{id}          обновить меню конкретного ресторана(только админ)
------------------------------------------------------------------------------------------------------------------------
- DELETE    /api/v1/restaurants/{id}/menu/{id}          удалить меню конкретного ресторана(только админ)
------------------------------------------------------------------------------------------------------------------------
- DELETE    /api/v1/restaurants/{id}                    удалить конкретный ресторан(только админ)
------------------------------------------------------------------------------------------------------------------------
- POST      /api/v1/restaurants/{id}/menu/{id}/votes    добавить голос за меню ресторана
------------------------------------------------------------------------------------------------------------------------
# Restaurant vote system

The graduation task of the TopJava online project

## Prerequisites

```
- Java 11 or higher
- Maven
```

Built With
```
- Spring Boot
- Spring Security
- Maven
- H2 Database
- Lombok
- Jaymes Young - Moondust [Official Audio]
```


## Build and run project

```
To build project and run: mvn run
```

## Example cURL queries

Get restaurant by Id  **/api/v1/restaurants/{id}**

```
curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004
```

Get all restaurants  **/api/v1/restaurants/**

```
curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants
```

Delete restaurant by Id  **/api/v1/restaurants/{id}**

```
curl -u admin.one@gmail.com:admin -X DELETE http://localhost:8080/api/v1/restaurants/1007
```
Add restaurant   **/api/v1/restaurants/**

```
curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants -H 'Content-Type: application/json' -d '{"name":"Shushary"}'
```
Add restaurant`s menu **/api/v1/restaurants/{id}/menu**

```
curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants/1004/menu -H 'Content-Type: application/json' -d '{"date":"2020-05-02"}'
```
Get restaurant`s menu by Id  **/api/v1/restaurants/{id}/menu**

```
curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004/menu
```
Get menu by Id  **/api/v1/restaurants/{id}/menu/{id}**

```
curl -s -u user.one@mail.ru:password http://localhost:8080/api/v1/restaurants/1004/menu/1008
```
Update menu by Id  **/api/v1/restaurants/{id}/menu/{id}**

```
curl -s -u admin.one@gmail.com:admin -X PUT http://localhost:8080/api/v1/restaurants/1004/menu/1008 -H 'Content-Type: application/json' -d '{"date":"2020-06-05"}'
```
Add dish by menu and restaurant id  **/api/v1/restaurants/{id}/menu/{id}/dish**

```
curl -s -u admin.one@gmail.com:admin -X POST http://localhost:8080/api/v1/restaurants/1004/menu/1008/dish -H 'Content-Type: application/json' -d '{"name":"New","price":40}'
```
Update dish by menu and restaurant id   **/api/v1/restaurants/{id}/menu/{id}/dish{id}**

```
curl -s -u admin.one@gmail.com:admin -X PUT http://localhost:8080/api/v1/restaurants/1004/menu/1008/dish/1016 -H 'Content-Type: application/json' -d '{"name":"NewTwo","price":30}'
```
Add vote by restaurant Id  **/api/v1/restaurants/{id}/menu/{id}/votes**

(To tests, change time in VoteServiceImpl.doVote 52 line code)

```
curl -s -u user.one@mail.ru:password -X POST http://localhost:8080/api/v1/restaurants/1004/votes
```







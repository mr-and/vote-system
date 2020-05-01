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
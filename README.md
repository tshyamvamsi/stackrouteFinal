# CMatches - A Case Study

## Problem Statement

Cricket is one of international sport and it is widely popular in India.
 
This case study is about showing current and old cricket matches played around the world. Also the details about each match including score and other statistics has to be displayed.

Build a system to find current cricket matches, bookmark favourite matches and recommend most liked/favourite matches to user.

## Requirements

### The application needs to fetch cricket matches from the following API.
https://www.cricapi.com/

Refer the following URLs to explore more on the cricket match APIs.
https://www.cricapi.com/how-to-use.aspx
https://www.cricapi.com/how-to-use/next-matches-api.aspx
https://www.cricapi.com/how-to-use/match-api.aspx
https://www.cricapi.com/how-to-use/scores-api.aspx

### A frontend where the user can register/login to the application, find current or old cricket matches, add interested matches to favourite list and view recommended matches.
### The upcoming cricket schedule can be displayed on the home page as a calendar as a quick view to the user. This can be viewed after successful login into the application.
### The complete match statistics can be displayed for a selected cricket match.
### User can add a match to favourite list and should be able to view favourite matches.
### A recommendation engine should be able to store all the unique favourite matches from all the users and maintain counter for number of users added a particular match into favourite list. 
### An option to view recommended matches should be available on frontend. 

## Microservices/Modules

- UserService - should be able to manage user accounts.
- UI (User interface) -  should be able to
    - View cricket calendar
    - View current matches
    - Add a match to favourite list
    - view favourite matches
    - view recommended matches
- UI should be responsive which can run smoothly on various devices 
- FavouriteService - should be able to store all the favourite matches for a user
- MatchRecommendationService - should be able to store all the unique favourite matches from all the users and maintain counter for number of users added a particular match into favourite list.

## Tech Stack

- Spring Boot
- API Gateway
- Message Broker (RabbitMQ)
- Angular
- CI (Gitlab Runner)
- Docker, Docker Compose

## Flow of Modules

- Building frontend:
  1. Building responsive views:
    - Register/Login
    - Cricket Matches - populating from external API
    - Build a view to show favourite matches
    - Build a view to show recommended matches
  2. Using Services to populate these data in views
  3. Stitching these views using Routes and Guards
  4. Making the UI Responsive
  5. E2E test cases and unit test cases
  6. Writing CI configuration file
  7. Dockerize the frontend

- Building the UserService
  1. Creating a server in Spring Boot to facilitate user registration and login using JWT token and MySQL
  2. Writing swagger documentation
  3. Unit Testing
  4. Write CI Configuration
  5. Dockerize the application
  6. Write docker-compose file to build both frontend and backend application

- Create an API Gateway which can serve UI and API Request from same host

- Building the FavouriteService
  1. Building a server in Spring Boot to facilitate CRUD operation over favourite matches stored in MySQL
  2. Writing Swagger Documentation
  3. Build a Producer for RabbitMQ which:
    - i. Produces events like what user added into favourite list
  4. Write Unit Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose

- Building the MatchRecommendationService
  1. Building a Consumer for RabbitMQ
     - i. On a new event generated Update the recommendations in the system
     - ii. Maintain list of recommended matches based on what user added into favourite list and keep counter for number of users added a particular match into favourite list
  2. Build an API to get Recommendations
  3. Writing Swagger Documentation
  4. Write Unit Test Cases
  5. Write CI Configuration
  6. Dockerize the application
  7. Update the docker-compose
  8. Update the API Gateway

- Demonstrate the entire application
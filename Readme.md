# Users-News Portal API
Vincent Mwangi Inc.
## Description
News Portal is a  REST API for querying and retrieving information on news departments and users. 

## Tech Stacks used
Java & Gradle & maven
Spark
Junit for testing
PostgreSQL database

## Deployment
 Api deployed on Heroku

## Installation
### Database

CREATE TABLE users (id serial primary key, username varchar, user_position varchar, user_role varchar, user_department varchar);
CREATE TABLE news (id serial primary key, title varchar, description varchar, type varchar, author varchar);
CREATE TABLE departments (id serial primary key, dept_name varchar, dept_description varchar, dept_totalemployees int);
CREATE TABLE departments_users (id serial primary key, deptid int, userid int);
CREATE TABLE departments_news (id serial primary key, deptid int, newsid int, userid int);

## Endpoints
Use the following paths.
/users Lists all the users
/departments Gets all the departments
/departments/new Gets an individual department using id
/departments/:deptId/details Post a new department
/departments/:deptId/users/new Creates new department from specific user
/departments/:deptId/users/:userId/details" Users from individual department
/departments/:deptId/users/:userId/news Users from individual department
/departments/:deptId/users/:userId/news/new" A user can post news
/departments/:deptId/news Get news from specific department
/users/:userId/news Get news from specific use news
/news Get all news 

## Livelink

<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817722-d1982d00-b8b3-11eb-8909-31dc8a2c42aa.png" width=500>
</p>

# PostManager

This is an application to manage posts from public API `https://jsonplaceholder.typicode.com/posts`

## Technologies

- Java: 11
- Database: H2
- Build automation tool: Apache Maven
- Spring Boot starter version: 2.4.5
- UI framework: Thymeleaf

## Post structure

```python
    {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere optio reprehenderit",
        "body": "quia et su autem sunt rem eveniet architecto"
    }
```

## Features

Things you can do with PostManager:

* Fetch posts from public API
* Show list of posts except userId
* Edit selected post's title or/and body (validating input text)
* Filter list by title
* Delete a selected post
* Regular updating of data (every 24 hours)
* Data can be updated on demand by clicking button

## Setup

* Build the project using `mvn verify`
* Run `mvn spring-boot:run` to quickly compile and run an application
* Web application is accessible by homepage ⬇️

```python
http://localhost:8080
```

## UI Screenshots

<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817722-d1982d00-b8b3-11eb-8909-31dc8a2c42aa.png" width=800>
</p>
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817721-d1982d00-b8b3-11eb-8713-c3f0c6ee1e20.png" width=800>
</p>
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817707-ccd37900-b8b3-11eb-9ab0-f5399424d02b.png" width=800>
</p>
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817719-d0ff9680-b8b3-11eb-9835-89829a53d287.png" width=800>
</p>
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/118817718-d0ff9680-b8b3-11eb-93d7-bd50a739b3a7.png" width=800>
</p>

## POSTMAN Rest Screenshots

`GET` all elements - `localhost:8080/getAll`
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/119146941-6bdea900-ba4b-11eb-8be8-056dc0b50119.png" width=800>
</p>

`GET` element by id - `localhost:8080/{id}`
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/119146938-6b461280-ba4b-11eb-8a2c-ea4a72aba92e.png" width=800>
</p>

`PUT` edit title and body by id with a JSON body - `localhost:8080/{id}`
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/119146931-6a14e580-ba4b-11eb-9185-01c27b7c7377.png" width=800>
</p>

`DELETE` delete post by id - `localhost:8080/{id}`
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/119146937-6aad7c00-ba4b-11eb-91a7-13a35a1d5979.png" width=800>
</p>

`GET` filter list by keyword in title - `localhost:8080/filter/{keyword}`
<p align="center">
  <img src = "https://user-images.githubusercontent.com/48919716/119146926-684b2200-ba4b-11eb-950e-89fad54a5a8c.png" width=800>
</p>
# SpringBookshelf-A-Java-API-Odyssey
SpringBookshelf is a dynamic backend project developed with Java, Spring Boot, and JPA, emphasizing efficient RESTful API operations. Users can effortlessly manage book details, including adding, viewing, updating, and deleting information. The platform also supports CRUD operations for user details, providing a versatile solution for seamless book and user management. The outcomes of the project can be tested using Postman, and changes can be verified using MySQL Workbench. The entire project is crafted on the Eclipse Enterprise IDE, ensuring a comprehensive development environment.
# Technologies used:
- Java
- Spring Boot
- RestAPI
- JPA (Java Persistence API)
- MySQL Workbench
- Postman
- Eclipse Enterprise IDE
# How To RUN:
> Follow These Steps:
1) Carefully Import the Project in your Eclipse
2) Expand the project in your workspace
3) Expand src/main/java
4) Expand com package
5) Open MySpringBootRunner.java
6) Right click inside the MySpringBootRunner.java file
7) Click Run as Java Application
8) Then Open your Postman to send HTTP Requests
9) Verify the Changes in your MYSQL Workbench
# Postman HTTP Request (Example):
> User CRUD:
- GET : http://localhost:999/api_grp1/users
- GET : http://localhost:999/api_grp1/users/{userid}
- POST: http://localhost:999/api_grp1/users
- PUT: http://localhost:999/api_grp1/users/{userid}/books/{bookid}
- PUT: http://localhost:999/api_grp1/users/{userid}
- DELETE: http://localhost:999/api_grp1/users/{userid}

> Book CRUD:
- GET : http://localhost:999/api_grp2/books
- GET : http://localhost:999/api_grp2/books/{bookid}
- POST: http://localhost:999/api_grp2/books
- PUT: http://localhost:999/api_grp2/books/{bookid}
- DELETE: http://localhost:999/api_grp2/books/{bookid}
# Postman HTTP Request Body (Example):
> User POST & PUT Raw Data:
- **POST: http://localhost:999/api_grp1/users :** <br/>
```
{     
       "name": "Kritika Maurya",
        "emailId": "mkriti12@gmail.com",
        "occupation": "Python Dev",
        "books": [
		
	{
   
    "author": "Chetan Bhagat",
    "isbn": 1538,
    "price": 2200,
    "title": "2-States",
    "type": "Novel"
},
{
    
    "author": "Héctor García",
    "isbn": 1534,
    "price": 3700,
    "title": "Ikgai",
    "type": "Non-Fiction"
}
		]
    }
```

- **PUT: http://localhost:999/api_grp1/users/{userid} :** <br/>
```
{
    "userId": 7,
    "name": "Ishita Maurya",
    "emailId": "ishitamaurya123@gmail.com",
    "occupation": "Java Developer"
}
```

- **PUT: http://localhost:999/api_grp1/users/{userid}/books/{bookid} :** <br/>
```
{
      "author": "Chetan Bhagat",
      "isbn": 1538,
      "price": 7900,
      "title": "2States",
      "type": "Novel"
    }
```

> Book POST & PUT Raw Data:   
- **POST: http://localhost:999/api_grp2/books :** <br/>
```
{
  "title": "Sample Book",
  "author": "John Doe",
  "isbn": 123456789,
  "price": 2999,
  "type": "Fiction",
  "user": {
    "userId": 7,
    "name": "Ishita Maurya",
    "emailId": "mishi237@gmail.com",
    "occupation": "Java Developer"
  }
}
```

- **PUT: http://localhost:999/api_grp2/books/{bookid} :** <br/> 
```
{
   
    "author": "Chetan Bhagat",
    "isbn": 1538,
    "price": 2200,
    "title": "2-States",
    "type": "Novel"
}
```

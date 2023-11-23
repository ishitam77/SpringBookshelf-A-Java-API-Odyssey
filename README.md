# SpringBookshelf-A-Java-API-Odyssey
SpringBookshelf is a dynamic backend project developed with Java, Spring Boot, and JPA, emphasizing efficient RESTful API operations. Users can effortlessly manage book details, including adding, viewing, updating, and deleting information. The platform also supports CRUD operations for user details, providing a versatile solution for seamless book and user management. The outcomes of the project can be tested using Postman, and changes can be verified using MySQL Workbench. The entire project is crafted on the Eclipse Enterprise IDE, ensuring a comprehensive development environment.
# Technologies used:
- Java
- Spring Boot
- JPA (Java Persistence API)
- MySQL Workbench
- Postman
- Eclipse Enterprise IDE
# Postman HTTP Request (Example):
User CRUD:
- GET : http://localhost:999/api_grp1/users
- GET : http://localhost:999/api_grp1/users/{userid}
- POST: http://localhost:999/api_grp1/users
- PUT: http://localhost:999/api_grp1/users/{userid}/books/{bookid}
- PUT: http://localhost:999/api_grp1/users/{userid}
- DELETE: http://localhost:999/api_grp1/users/{userid}

Book CRUD:
- GET : http://localhost:999/api_grp2/books
- GET : http://localhost:999/api_grp2/books/{bookid}
- POST: http://localhost:999/api_grp2/books
- PUT: http://localhost:999/api_grp2/books/{bookid}
- DELETE: http://localhost:999/api_grp2/books/{bookid}
# Postman HTTP Request Body (Example):
User POST & PUT Raw Data:
- POST: http://localhost:999/api_grp1/users
---ruby
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
---

- PUT: http://localhost:999/api_grp1/users/{userid}

{
        "name": "Ishita Maurya",
        "emailId": "mishi237@gmail.com",
        "occupation": "Java Developer",
        "books": [
		
	{
    "author": "Chetan Bhagat",
    "isbn": 1438,
    "price": 2070,
    "title": "3 Mistakes of My Life",
    "type": "Novel"
},
{
    "author": "Arun Tiwari",
    "isbn": 1434,
    "price": 3210,
    "title": "Wings of Fire",
    "type": "AutoBiography"
}
		]
    }

- PUT: http://localhost:999/api_grp1/users/{userid}/books/{bookid}

    {
      "author": "Chetan Bhagat",
      "isbn": 1538,
      "price": 7900,
      "title": "2States",
      "type": "Novel"
    }

Book POST & PUT Raw Data:   
- POST: http://localhost:999/api_grp2/books

{
  "title": "Sample Book",
  "author": "John Doe",
  "isbn": 123456789,
  "price": 2999,
  "type": "Fiction",
  "user": {
    "userId": 7,
    "name": "John Doe",
    "emailId": "mishi237@gmail.com",
    "occupation": "Java Dev"
  }
}

- PUT: http://localhost:999/api_grp2/books/{bookid}

{
   
    "author": "Chetan Bhagat",
    "isbn": 1538,
    "price": 2200,
    "title": "2-States",
    "type": "Novel"
}


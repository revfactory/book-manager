# Book Management Service
The Book Management Service is a web application built with Kotlin and Spring Boot that allows users to manage books and book categories.

## Features
- Add, update, and delete books  
- Search for books by keyword
- Add, update, and delete book categories
- Add and remove books from book categories

## Requirements
- Java 17 or higher
- Gradle 7.6 or higher

## Installation
- Clone the repository: git clone https://github.com/revfactory/book-manager.git
- Navigate to the project directory: cd book-manager
- Build the project: ./gradlew build
- Run the project: ./gradlew bootRun

## Usage
The Book Management Service provides a REST API that can be accessed using any HTTP client, such as cURL or Postman.

The API is documented using OpenAPI (formerly known as Swagger), which provides an interactive documentation UI that allows users to explore the API and try out requests.

## Authentication
The Book Management Service uses HTTP Basic authentication to authenticate requests. To access the API, users must provide a username and password that correspond to a user with the appropriate role.

### The following roles are defined:

- USER: This role allows users to view books and book categories.
- MANAGER: This role allows users to add, update, and delete books and book categories.

### The default username and password for the MANAGER role are:

- Username: manager
- Password: password

### The default username and password for the USER role are:

- Username: user
- Password: password

## Endpoints
The following endpoints are available:

### Books
- POST /books: Add a new book (MANAGER role required).
- GET /books: Get a list of all books (USER role required).
- GET /books/search: Search for books by keyword (USER role required).
- GET /books/{bookId}: Get a book by ID (USER role required).
- PUT /books/{bookId}: Update a book (MANAGER role required).
- DELETE /books/{bookId}: Delete a book (MANAGER role required).

### Book Categories
- POST /categories: Add a new book category (MANAGER role required).
- GET /categories: Get a list of all book categories (USER role required).
- GET /categories/{categoryId}: Get a book category by ID (USER role required).
- PUT /categories/{categoryId}: Update a book category (MANAGER role required).
- DELETE /categories/{categoryId}: Delete a book category (MANAGER role required).
- POST /categories/{categoryId}/books: Add a book to a book category (MANAGER role required).
- GET /categories/{categoryId}/books: Get a list of books in a book category (USER role required).
- DELETE /categories/{categoryId}/books/{bookId}: Remove a book from a book category (MANAGER role required).


## License
This project is licensed under the MIT License - see the LICENSE file for details.



## Reference 
* This project is only made with ChatGPT.
* API 스펙 샘플은 [구글 엑셀](https://docs.google.com/spreadsheets/d/1jjumPWpmNbc_wXUaUrd_wvwqoZ7xGHpieHYTTJvtStU/edit?usp=sharing)에 있습니다.
* 자세한 과정은 다음 글을 참고해주세요.: https://careerly.co.kr/comments/78184

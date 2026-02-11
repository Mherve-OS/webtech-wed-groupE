# Spring Boot RESTful API Assignment
# Question 1: Library Book Management

Create a BookController with the following endpoints:

GET /api/books - Return a list of all books (create 3 sample books in a List)
![alt text](image.png)

GET /api/books/{id} - Return a specific book by ID
![alt text](image-1.png)

GET /api/books/search?title={title} - Search books by title
![alt text](image-2.png)

POST /api/books - Add a new book (accept Book object in request body)
![alt text](image-3.png)

DELETE /api/books/{id} - Delete a book by ID
![alt text](image-4.png)

# Question 2: Student Registration API

Create a StudentController with these endpoints:

GET /api/students - Get all students
![alt text](image-5.png)

GET /api/students/{studentId} - Get student by ID
![alt text](image-7.png)

GET /api/students/major/{major} - Get all students by major (use path
variable)
![alt text](image-8.png)

GET /api/students/filter?gpa={minGpa} - Filter students with GPA greater than
or equal to minimum
![alt text](image-9.png)

POST /api/students - Register a new student
![alt text](image-10.png)

PUT /api/students/{studentId} - Update student information
![alt text](image-11.png)

# Question 3: Restaurant Menu API

Create a MenuController with endpoints:

GET /api/menu - Get all menu items
![alt text](image-12.png)

GET /api/menu/{id} - Get specific menu item
![alt text](image-13.png)

GET /api/menu/category/{category} - Get items by category
![alt text](image-14.png)

GET /api/menu/available - Get only available items (use query
parameter: ?available=true)
![alt text](image-15.png)

GET /api/menu/search?name={name} - Search menu items by name
![alt text](image-16.png)

POST /api/menu - Add new menu item
![alt text](image-17.png)

PUT /api/menu/{id}/availability - Toggle item availability
![alt text](image-18.png)

DELETE /api/menu/{id} - Remove menu item
![alt text](image-19.png)

# Question 4: E-Commerce Product API

Create a ProductController with these endpoints:

GET /api/products - Get all products (with optional pagination
params: ?page={page}&limit={limit})
![alt text](image-20.png)








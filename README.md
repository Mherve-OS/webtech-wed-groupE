# Spring Boot RESTful API Assignment

A comprehensive RESTful API project implementing 5 different REST APIs as part of Spring Boot Module 1-3 assignment.

## 📋 Project Overview

This project contains implementations for:
- **Question 1**: Library Book Management API
- **Question 2**: Student Registration API
- **Question 3**: Restaurant Menu API
- **Question 4**: E-Commerce Product Catalog API
- **Question 5**: Task Management API
- **Bonus**: User Profile Management API (with ApiResponse wrapper)

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Web**
- **Maven**
- **Embedded Tomcat Server**

## 📂 Project Structure

```
restfullApiAssignment/
├── src/
│   ├── main/
│   │   ├── java/auca/ac/rw/restfullApiAssignment/
│   │   │   ├── RestfullApiAssignmentApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── library/BookController.java
│   │   │   │   ├── studentRegistration/StudentController.java
│   │   │   │   ├── restaurant/MenuController.java
│   │   │   │   ├── ecommerce/ProductController.java
│   │   │   │   ├── taskmanagement/TaskController.java
│   │   │   │   └── userprofile/UserProfileController.java
│   │   │   └── modal/
│   │   │       ├── library/Book.java
│   │   │       ├── studentRegistration/Student.java
│   │   │       ├── restaurant/MenuItem.java
│   │   │       ├── ecommerce/Product.java
│   │   │       ├── taskmanagement/Task.java
│   │   │       └── userprofile/
│   │   │           ├── UserProfile.java
│   │   │           └── ApiResponse.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── README.md
└── RestfulAPI_Postman_Collection.json
```

## 🚀 How to Run

### Prerequisites
- Java 21 or higher installed
- Maven installed (or use included Maven wrapper)

### Steps

1. **Navigate to project directory**
   ```bash
   cd restfullApiAssignment
   ```

2. **Build the project**
   ```bash
   ./mvnw.cmd clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw.cmd spring-boot:run
   ```

4. **Access the APIs**
   - Base URL: `http://localhost:8081`
   - Server runs on port **8081** (configured in application.properties)

## 📚 API Documentation

### Question 1: Library Book Management API

**Base Path**: `/api/books`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/books` | Get all books | 200 |
| GET | `/api/books/{id}` | Get book by ID | 200, 404 |
| GET | `/api/books/search?title={title}` | Search books by title | 200 |
| POST | `/api/books` | Add new book | 201 |
| DELETE | `/api/books/{id}` | Delete book | 204, 404 |

**Sample Request - Add New Book:**
```json
POST /api/books
{
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "publicationYear": 2016
}
```

**Sample Response:**
```json
{
  "id": 4,
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "publicationYear": 2016
}
```

---

### Question 2: Student Registration API

**Base Path**: `/api/students`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/students` | Get all students | 200 |
| GET | `/api/students/{studentId}` | Get student by ID | 200, 404 |
| GET | `/api/students/major/{major}` | Get students by major | 200 |
| GET | `/api/students/filter?gpa={gpa}` | Filter students by GPA | 200 |
| POST | `/api/students` | Register new student | 201 |
| PUT | `/api/students/{studentId}` | Update student info | 200, 404 |

**Sample Request - Register Student:**
```json
POST /api/students
{
  "firstName": "Frank",
  "lastName": "Mugisha",
  "email": "frank@auca.ac.rw",
  "major": "Computer Science",
  "gpa": 3.7
}
```

**Test Scenarios:**
- Get students with major "Computer Science": `/api/students/major/Computer Science`
- Get students with GPA >= 3.5: `/api/students/filter?gpa=3.5`

---

### Question 3: Restaurant Menu API

**Base Path**: `/api/menu`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/menu` | Get all menu items | 200 |
| GET | `/api/menu/{id}` | Get menu item by ID | 200, 404 |
| GET | `/api/menu/category/{category}` | Get items by category | 200 |
| GET | `/api/menu/available?available=true` | Get available items | 200 |
| GET | `/api/menu/search?name={name}` | Search by name | 200 |
| POST | `/api/menu` | Add new menu item | 201 |
| PUT | `/api/menu/{id}/availability` | Toggle availability | 200, 404 |
| DELETE | `/api/menu/{id}` | Delete menu item | 204, 404 |

**Categories**: Appetizer, Main Course, Dessert, Beverage

**Sample Request:**
```json
POST /api/menu
{
  "name": "Grilled Chicken",
  "description": "Marinated grilled chicken with herbs",
  "price": 16.99,
  "category": "Main Course",
  "available": true
}
```

---

### Question 4: E-Commerce Product API

**Base Path**: `/api/products`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/products` | Get all products (with pagination) | 200 |
| GET | `/api/products?page=0&limit=5` | Get products with pagination | 200 |
| GET | `/api/products/{productId}` | Get product by ID | 200, 404 |
| GET | `/api/products/category/{category}` | Get products by category | 200 |
| GET | `/api/products/brand/{brand}` | Get products by brand | 200 |
| GET | `/api/products/search?keyword={keyword}` | Search in name/description | 200 |
| GET | `/api/products/price-range?min={min}&max={max}` | Filter by price range | 200 |
| GET | `/api/products/in-stock` | Get in-stock products | 200 |
| POST | `/api/products` | Add new product | 201 |
| PUT | `/api/products/{productId}` | Update product | 200, 404 |
| PATCH | `/api/products/{productId}/stock?quantity={qty}` | Update stock | 200, 404 |
| DELETE | `/api/products/{productId}` | Delete product | 204, 404 |

**Sample Request:**
```json
POST /api/products
{
  "name": "iPad Pro",
  "description": "12.9-inch tablet with M2 chip",
  "price": 1099.99,
  "category": "Electronics",
  "stockQuantity": 25,
  "brand": "Apple"
}
```

**Test Examples:**
- Get Electronics: `/api/products/category/Electronics`
- Get Apple products: `/api/products/brand/Apple`
- Price range $50-$200: `/api/products/price-range?min=50&max=200`
- Search keyword: `/api/products/search?keyword=phone`

---

### Question 5: Task Management API

**Base Path**: `/api/tasks`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/tasks` | Get all tasks | 200 |
| GET | `/api/tasks/{taskId}` | Get task by ID | 200, 404 |
| GET | `/api/tasks/status?completed={bool}` | Filter by completion status | 200 |
| GET | `/api/tasks/priority/{priority}` | Get tasks by priority | 200 |
| POST | `/api/tasks` | Create new task | 201 |
| PUT | `/api/tasks/{taskId}` | Update task | 200, 404 |
| PATCH | `/api/tasks/{taskId}/complete` | Mark as completed | 200, 404 |
| DELETE | `/api/tasks/{taskId}` | Delete task | 204, 404 |

**Priority Levels**: LOW, MEDIUM, HIGH

**Sample Request:**
```json
POST /api/tasks
{
  "title": "Submit Assignment",
  "description": "Push code to Git and submit",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-11"
}
```

---

### Bonus: User Profile API

**Base Path**: `/api/users`

| Method | Endpoint | Description | Status Code |
|--------|----------|-------------|-------------|
| GET | `/api/users` | Get all users | 200 |
| GET | `/api/users/{userId}` | Get user by ID | 200, 404 |
| GET | `/api/users/search/username?username={name}` | Search by username | 200 |
| GET | `/api/users/search/country/{country}` | Search by country | 200 |
| GET | `/api/users/search/age?min={min}&max={max}` | Search by age range | 200 |
| POST | `/api/users` | Create new user | 201 |
| PUT | `/api/users/{userId}` | Update user profile | 200, 404 |
| PATCH | `/api/users/{userId}/activate` | Activate user | 200, 404 |
| PATCH | `/api/users/{userId}/deactivate` | Deactivate user | 200, 404 |
| DELETE | `/api/users/{userId}` | Delete user | 200, 404 |

**Note**: This API uses `ApiResponse<T>` wrapper for all responses.

**Sample Response:**
```json
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "age": 25,
    "country": "Rwanda",
    "bio": "Software developer",
    "active": true
  }
}
```

## 🧪 Testing with Postman

1. Import the **RestfulAPI_Postman_Collection.json** file into Postman
2. The collection includes 50+ pre-configured requests
3. All requests are organized by question number
4. Base URL variable is pre-configured as `http://localhost:8081`

### Quick Test URLs (Browser):
- Books: http://localhost:8081/api/books
- Students: http://localhost:8081/api/students
- Menu: http://localhost:8081/api/menu
- Products: http://localhost:8081/api/products
- Tasks: http://localhost:8081/api/tasks
- Users: http://localhost:8081/api/users

## 📊 Sample Data

The application comes pre-loaded with sample data:
- **3 Books** (Clean Code, Effective Java, Design Patterns)
- **5 Students** (various majors and GPAs)
- **8 Menu Items** (across all categories)
- **10 Products** (Electronics, Clothing, Footwear, Books)
- **5 Tasks** (various priorities and statuses)
- **5 User Profiles** (from different countries)

## 🎯 HTTP Status Codes Used

- `200 OK` - Successful GET, PUT, PATCH requests
- `201 Created` - Successful POST requests
- `204 No Content` - Successful DELETE requests
- `404 Not Found` - Resource not found

## 👨‍💻 Developer Notes

### Package Structure
- **Controllers**: Organized by feature (library, studentRegistration, restaurant, etc.)
- **Models**: Matching package structure under `modal/`
- Each question has its own isolated package to maintain separation

### Data Storage
- All data is stored in-memory using `ArrayList`
- Data resets when application restarts
- IDs are auto-generated using incremental counters

### Best Practices Implemented
- Proper REST conventions (GET, POST, PUT, PATCH, DELETE)
- Appropriate HTTP status codes
- Clean code with meaningful variable names
- Proper use of annotations (@RestController, @RequestMapping, etc.)
- Request/response body handling with @RequestBody
- Path variables with @PathVariable
- Query parameters with @RequestParam

## 📝 Assignment Submission

**Branch name**: `restFull_api_StudentId`

**Submission includes**:
- ✅ Complete source code
- ✅ All 5 questions + bonus implemented
- ✅ README.md with documentation
- ✅ Postman collection for testing
- ✅ Sample data pre-loaded
- ✅ Proper HTTP methods and status codes

## 📞 Support

For issues or questions, please refer to the assignment PDF or contact your instructor.

---

**Project by**: AUCA Student  
**Course**: Spring Boot RESTful API Development  
**Module**: 1-3 Introduction to Spring Boot & Building REST Controllers

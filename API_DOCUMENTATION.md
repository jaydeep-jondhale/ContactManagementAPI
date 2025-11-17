# Contact Management API

A Spring Boot REST API for managing contacts using H2 in-memory database.

## Project Structure

```
src/main/java/com/contactmanagement/
├── entity/
│   └── Contact.java          # JPA Entity for Contact
├── repository/
│   └── ContactRepository.java # JPA Repository for database operations
├── service/
│   └── ContactService.java    # Business logic layer
├── controller/
│   └── ContactController.java # REST API endpoints
└── ContactmanagementApplication.java # Main Spring Boot application
```

## Technologies Used

- **Java 19**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database (In-Memory)**
- **Lombok** (for reducing boilerplate)
- **Gradle** (Build tool)

## Features

- ✅ Create new contacts
- ✅ Read/Get all contacts
- ✅ Get contact by ID
- ✅ Get contact by email
- ✅ Update existing contacts
- ✅ Delete contacts
- ✅ CORS enabled for cross-origin requests
- ✅ H2 console for database inspection

## Contact Model

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "phoneNumber": "+1-555-1234",
  "address": "123 Main St",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001"
}
```

## API Endpoints

### Base URL
```
http://localhost:8080/api/contacts
```

### 1. Get All Contacts
```
GET /api/contacts
Response: 200 OK
Body: [
  { contact object },
  ...
]
```

### 2. Get Contact by ID
```
GET /api/contacts/{id}
Response: 200 OK (if found) / 404 NOT_FOUND
Body: { contact object }
```

### 3. Get Contact by Email
```
GET /api/contacts/email/{email}
Response: 200 OK (if found) / 404 NOT_FOUND
Body: { contact object }
```

### 4. Create New Contact
```
POST /api/contacts
Request Body:
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "phoneNumber": "+1-555-5678",
  "address": "456 Oak Ave",
  "city": "Boston",
  "state": "MA",
  "zipCode": "02101"
}
Response: 201 CREATED
Body: { created contact object with id }
```

### 5. Update Contact
```
PUT /api/contacts/{id}
Request Body: { updated contact fields }
Response: 200 OK (if found) / 404 NOT_FOUND
Body: { updated contact object }
```

### 6. Delete Contact
```
DELETE /api/contacts/{id}
Response: 204 NO_CONTENT (if deleted) / 404 NOT_FOUND
```

## Database Configuration

The application uses H2 in-memory database with the following settings:

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:contactdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Console (accessible at http://localhost:8080/h2-console)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Running the Application

### Build the project
```bash
./gradlew build
```

### Run the application
```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`

## Accessing H2 Console

Navigate to: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:contactdb`
- **User Name**: `sa`
- **Password**: (leave empty)

## Example Usage with cURL

### Create a contact
```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "+1-555-1234",
    "address": "123 Main St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
  }'
```

### Get all contacts
```bash
curl http://localhost:8080/api/contacts
```

### Get contact by ID
```bash
curl http://localhost:8080/api/contacts/1
```

### Update a contact
```bash
curl -X PUT http://localhost:8080/api/contacts/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane@example.com",
    "phoneNumber": "+1-555-9999",
    "address": "789 Elm St",
    "city": "Boston",
    "state": "MA",
    "zipCode": "02101"
  }'
```

### Delete a contact
```bash
curl -X DELETE http://localhost:8080/api/contacts/1
```

## Notes

- Email must be unique for each contact
- FirstName, LastName, Email, and PhoneNumber are required fields
- Address fields (address, city, state, zipCode) are optional
- The database is in-memory, so it will reset when the application restarts
- All timestamps are in UTC+5:30 (IST)


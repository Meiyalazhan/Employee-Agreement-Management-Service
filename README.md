###Employee Agreement Management Service
This project is a Spring Boot-based RESTful service for managing employment agreements. It includes features such as adding, updating, retrieving, and deleting employment agreements, with validation and exception handling.

###Prerequisites
- Java Development Kit (JDK 8 or above)
- Maven (for dependency management)
- Eclipse IDE
- Postman or any other API testing tool
- Web Browser (for H2 Console)

###Project Setup
Clone or download the project from the repository.

##Import the project into Eclipse IDE:
- Open Eclipse IDE.
- Go to File > Import.
- Select Maven > Existing Maven Projects.
- Browse to the root directory of the project (EmployeeAgreement) and click Finish.
- Locate the EmployeeAgreementApplication.java file in the src/main/java/com/gorai/employee directory.
- Right-click on EmployeeAgreementApplication.java and select Run As > Spring Boot Application.

##Database Setup
Open your web browser and navigate to the H2 Console: http://localhost:8080/h2-console
#Enter the following details:
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave it blank)
Click Connect. You will be connected to the in-memory H2 database.
Once connected, you can execute SQL commands or inspect the database schema.

##API Endpoints
Below are the available API endpoints for the service. Use Postman or any other API testing tool to test them.

###GET
- Retrieve All Employment Agreements
Endpoint: GET /api/employment-agreements
Description: Fetch a list of all employment agreements.
Example Request:
GET http://localhost:8080/api/employment-agreements
Example Response:
[
    {
        "id": 1,
        "employeeName": "John Doe",
        "role": "Software Engineer",
        "startDate": "2024-01-01",
        "endDate": "2025-12-31",
        "salary": 85000,
        "terms": "Full Time",
        "otherDetails": {
            "department": "Development",
            "manager": "Jane Smith"
        }
    }
]

- Retrieve Employment Agreement by ID
Endpoint: GET /api/employment-agreements/{id}
Description: Fetch a specific employment agreement by its ID.
Example Request:
GET http://localhost:8080/api/employment-agreements/1
Example Response:
{
    "id": 1,
    "employeeName": "John Doe",
    "role": "Software Engineer",
    "startDate": "2024-01-01",
    "endDate": "2025-12-31",
    "salary": 85000,
    "terms": "Full Time",
    "otherDetails": {
        "department": "Development",
        "manager": "Jane Smith"
    }

}
###POST
- Create a New Employment Agreement
Endpoint: POST /api/employment-agreements
Description: Create a new employment agreement.
Request Body:
{
    "employeeName": "John Doe",
    "role": "Software Engineer",
    "startDate": "2024-01-01",
    "endDate": "2025-12-31",
    "salary": 85000,
    "terms": "Full Time",
    "otherDetails": {
        "department": "Development",
        "manager": "Jane Smith"
    }
}

Example Response:
{
    "id": 2,
    "employeeName": "John Doe",
    "role": "Software Engineer",
    "startDate": "2024-01-01",
    "endDate": "2025-12-31",
    "salary": 85000,
    "terms": "Full Time",
    "otherDetails": {
        "department": "Development",
        "manager": "Jane Smith"
    }
}
###PATCH
- Update Part of an Employment Agreement
Endpoint: PATCH /api/employment-agreements/{id}
Description: Update specific fields of an existing employment agreement.
Request Body:
{
    "terms": "Part-Time"
}

Example Request:
PATCH http://localhost:8080/api/employment-agreements/1

Example Response:
{
    "id": 1,
    "employeeName": "John Doe",
    "role": "Software Engineer",
    "startDate": "2024-01-01",
    "endDate": "2025-12-31",
    "salary": 85000,
    "terms": "Part-Time",
    "otherDetails": {
        "department": "Development",
        "manager": "Jane Smith"
    }
}

###DELETE
- Delete an Employment Agreement
Endpoint: DELETE /api/employment-agreements/{id}
Description: Delete a specific employment agreement by its ID.
Request:
DELETE http://localhost:8080/api/employment-agreements/1

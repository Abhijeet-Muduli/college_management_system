# College Management System

A **Spring Boot + Spring Data JPA** project demonstrating real-world entity relationships and database operations.

### Tech Stack

`Java` • `Spring Boot` • `Spring Data JPA` • `Hibernate` • `H2 Database` • `Maven` • `REST API`

### JPA Relationships

* `@OneToMany / @ManyToOne` — Professor ↔ Subject
* `@ManyToMany` — Professor ↔ Student
* `@ManyToMany` — Student ↔ Subject
* `@OneToOne` — Student ↔ Admission Record

### Key Concepts

`Entity Mapping` • `JpaRepository` • `@JoinColumn` • `@JoinTable` • `mappedBy` • `CascadeType` • `FetchType` • `orphanRemoval` • `@Transactional` • DTOs • Validation • Exception Handling

A beginner-friendly project focused on understanding **Spring Data JPA relationships, Hibernate ORM, and relational database mapping**.
This project is build on Java 21.

Build the project:
mvn clean compile
Run tests
mvn test

Start the application:
mvn spring-boot:run

The application runs on: http://localhost:8080
Database: This project uses an in-memory H2 database.
Database URL: jdbc: h2:mem:college_db
Open: http://localhost:8080/h2-console
Username: sa
Password:(empty)

Check database tables
Run these SQL queries inside the H2 Console:

SHOW TABLES;
SELECT * FROM PROFESSORS;
SELECT * FROM STUDENTS;
SELECT * FROM SUBJECTS;
SELECT * FROM ADMISSION_RECORDS;
SELECT * FROM STUDENT_PROFESSOR;
SELECT * FROM STUDENT_SUBJECT;
Check Relationships
Professor → Subject
SELECT * FROM SUBJECTS;

Check the PROFESSOR_ID column.

Professor ↔ Student
SELECT * FROM STUDENT_PROFESSOR;
Student ↔ Subject
SELECT * FROM STUDENT_SUBJECT;
AdmissionRecord → Student
SELECT * FROM ADMISSION_RECORDS;

Check the STUDENT_ID column.

Testing APIs from Terminal

The following commands work in Windows PowerShell using curl.exe.

Create Professor
curl.exe -X POST "http://localhost:8080/api/professors" -H "Content-Type: application/json" -d '{"title":"Dr. Sharma"}'
Create Student
curl.exe -X POST "http://localhost:8080/api/students" -H "Content-Type: application/json" -d '{"name":"Abhijeet"}'
Create Subject
curl.exe -X POST "http://localhost:8080/api/subjects" -H "Content-Type: application/json" -d '{"title":"Spring Data JPA"}'
Create Admission Record
curl.exe -X POST "http://localhost:8080/api/admission-records" -H "Content-Type: application/json" -d '{"fees":50000}'
Assign Relationships
Professor → Subject
curl.exe -X PUT "http://localhost:8080/api/professors/1/subjects/1"
Professor ↔ Student
curl.exe -X PUT "http://localhost:8080/api/professors/1/students/1"
Student ↔ Subject
curl.exe -X PUT "http://localhost:8080/api/students/1/subjects/1"
AdmissionRecord → Student
curl.exe -X PUT "http://localhost:8080/api/admission-records/1/students/1"
Verify Relationships
Professor's Subjects
curl.exe "http://localhost:8080/api/professors/1/subjects"
Professor's Students
curl.exe "http://localhost:8080/api/professors/1/students"
Student's Professors
curl.exe "http://localhost:8080/api/students/1/professors"
Student's Subjects
curl.exe "http://localhost:8080/api/students/1/subjects"
Subject's Professor
curl.exe "http://localhost:8080/api/subjects/1/professor"
Subject's Students
curl.exe "http://localhost:8080/api/subjects/1/students"
Admission Record
curl.exe "http://localhost:8080/api/admission-records/1"

CRUD Endpoints:
Professor
POST   /api/professors
GET    /api/professors
GET    /api/professors/{id}
DELETE /api/professors/{id}

Student
POST   /api/students
GET    /api/students
GET    /api/students/{id}
DELETE /api/students/{id}

Subject
POST   /api/subjects
GET    /api/subjects
GET    /api/subjects/{id}
DELETE /api/subjects/{id}

Admission Record
POST   /api/admission-records
GET    /api/admission-records
GET    /api/admission-records/{id}
DELETE /api/admission-records/{id}

Important Notes
H2 is an in-memory database, so all data is cleared when the application restarts.
Hibernate automatically creates the required tables from the JPA entity mappings.
Relationship tables such as STUDENT_PROFESSOR and STUDENT_SUBJECT are used for @ManyToMany mappings.
@JoinColumn is used for foreign-key based relationships.
mappedBy identifies the inverse side of a relationship.
@Transactional is used in service-layer operations.

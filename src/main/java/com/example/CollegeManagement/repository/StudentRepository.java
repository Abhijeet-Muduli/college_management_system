package com.example.CollegeManagement.repository;

import com.example.CollegeManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

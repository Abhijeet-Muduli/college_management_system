package com.example.CollegeManagement.repository;

import com.example.CollegeManagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

package com.example.CollegeManagement.repository;

import com.example.CollegeManagement.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository
          extends JpaRepository<Professor, Long> {

}

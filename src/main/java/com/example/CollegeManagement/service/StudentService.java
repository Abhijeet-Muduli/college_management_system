package com.example.CollegeManagement.service;


import com.example.CollegeManagement.dto.IdNameResponse;
import com.example.CollegeManagement.dto.IdTitleResponse;
import com.example.CollegeManagement.dto.StudentRequest;

import com.example.CollegeManagement.entity.Professor;
import com.example.CollegeManagement.entity.Student;
import com.example.CollegeManagement.entity.Subject;

import com.example.CollegeManagement.exception.ResourceNotFoundException;

import com.example.CollegeManagement.repository.ProfessorRepository;
import com.example.CollegeManagement.repository.StudentRepository;
import com.example.CollegeManagement.repository.SubjectRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(
            StudentRepository studentRepository,
            ProfessorRepository professorRepository,
            SubjectRepository subjectRepository) {

        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public IdNameResponse create(
            StudentRequest request) {

        Student student =
                new Student(request.name());

        Student saved =
                studentRepository.save(student);

        return new IdNameResponse(
                saved.getId(),
                saved.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<IdNameResponse> findAll() {

        return studentRepository.findAll()
                .stream()
                .map(student ->
                        new IdNameResponse(
                                student.getId(),
                                student.getName()
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public IdNameResponse findById(Long id) {

        Student student =
                getStudent(id);

        return new IdNameResponse(
                student.getId(),
                student.getName()
        );
    }

    @Transactional
    public void delete(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Student not found: " + id
            );
        }

        studentRepository.deleteById(id);
    }


    @Transactional
    public void assignProfessor(
            Long studentId,
            Long professorId) {

        Student student =
                getStudent(studentId);

        Professor professor =
                professorRepository.findById(professorId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Professor not found: "
                                                + professorId
                                )
                        );

        student.addProfessor(professor);

        studentRepository.save(student);
    }

    @Transactional
    public void assignSubject(
            Long studentId,
            Long subjectId) {

        Student student =
                getStudent(studentId);

        Subject subject =
                subjectRepository.findById(subjectId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found: "
                                                + subjectId
                                )
                        );

        student.addSubject(subject);

        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<IdNameResponse>
    getProfessors(Long studentId) {

        Student student =
                getStudent(studentId);

        return student.getProfessors()
                .stream()
                .map(professor ->
                        new IdNameResponse(
                                professor.getId(),
                                professor.getTitle()
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public List<IdTitleResponse>
    getSubjects(Long studentId) {

        Student student =
                getStudent(studentId);

        return student.getSubjects()
                .stream()
                .map(subject ->
                        new IdTitleResponse(
                                subject.getId(),
                                subject.getTitle()
                        )
                )
                .toList();
    }

    private Student getStudent(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found: " + id
                        )
                );
    }
}

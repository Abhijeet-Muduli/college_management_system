package com.example.CollegeManagement.controller;

import com.example.CollegeManagement.dto.IdNameResponse;
import com.example.CollegeManagement.dto.IdTitleResponse;
import com.example.CollegeManagement.dto.MessageResponse;
import com.example.CollegeManagement.dto.StudentRequest;

import com.example.CollegeManagement.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(
            StudentService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<IdNameResponse> create(
            @Valid @RequestBody List<StudentRequest> requests) {

        return requests.stream()
                .map(service::create)
                .toList();
    }


    @GetMapping
    public List<IdNameResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IdNameResponse findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(
            @PathVariable Long id) {

        service.delete(id);

        return new MessageResponse(
                "Student deleted: " + id
        );
    }

    @PutMapping(
            "/{studentId}/professors/{professorId}"
    )
    public MessageResponse assignProfessor(
            @PathVariable Long studentId,
            @PathVariable Long professorId) {

        service.assignProfessor(
                studentId,
                professorId
        );

        return new MessageResponse(
                "Professor " + professorId +
                        " assigned to student "
                        + studentId
        );
    }

    @PutMapping(
            "/{studentId}/subjects/{subjectId}"
    )
    public MessageResponse assignSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {

        service.assignSubject(
                studentId,
                subjectId
        );

        return new MessageResponse(
                "Subject " + subjectId +
                        " assigned to student "
                        + studentId
        );
    }

    @GetMapping("/{id}/professors")
    public List<IdNameResponse>
    getProfessors(@PathVariable Long id) {

        return service.getProfessors(id);
    }

    @GetMapping("/{id}/subjects")
    public List<IdTitleResponse>
    getSubjects(@PathVariable Long id) {

        return service.getSubjects(id);
    }
}
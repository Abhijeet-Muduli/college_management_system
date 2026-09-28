package com.example.CollegeManagement.controller;

import com.example.CollegeManagement.dto.IdNameResponse;
import com.example.CollegeManagement.dto.IdTitleResponse;
import com.example.CollegeManagement.dto.MessageResponse;
import com.example.CollegeManagement.dto.SubjectRequest;

import com.example.CollegeManagement.service.SubjectService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(
            SubjectService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<IdTitleResponse> create(
            @Valid @RequestBody
            List<SubjectRequest> request) {

        return request.stream()
                .map(service::create)
                .toList();
    }

    @GetMapping
    public List<IdTitleResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public IdTitleResponse findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(
            @PathVariable Long id) {

        service.delete(id);

        return new MessageResponse(
                "Subject deleted: " + id
        );
    }

    @GetMapping("/{id}/professor")
    public IdNameResponse getProfessor(
            @PathVariable Long id) {

        return service.getProfessor(id);
    }

    @GetMapping("/{id}/students")
    public List<IdNameResponse> getStudents(
            @PathVariable Long id) {

        return service.getStudents(id);
    }
}

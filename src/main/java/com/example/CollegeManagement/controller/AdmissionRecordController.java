package com.example.CollegeManagement.controller;

import com.example.CollegeManagement.dto.AdmissionRecordRequest;
import com.example.CollegeManagement.dto.AdmissionRecordResponse;
import com.example.CollegeManagement.dto.MessageResponse;
import com.example.CollegeManagement.service.AdmissionRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admission-records")
public class AdmissionRecordController {

    private final AdmissionRecordService service;

    public AdmissionRecordController(
            AdmissionRecordService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<AdmissionRecordResponse> create(
            @Valid @RequestBody
            List<AdmissionRecordRequest> request) {

        return request.stream()
                .map(service::create)
                .toList();
    }

    @GetMapping
    public List<AdmissionRecordResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AdmissionRecordResponse findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(
            @PathVariable Long id) {

        service.delete(id);

        return new MessageResponse(
                "Admission record deleted: " + id
        );
    }

    @PutMapping(
            "/{recordId}/students/{studentId}"
    )
    public MessageResponse assignStudent(
            @PathVariable Long recordId,
            @PathVariable Long studentId) {

        service.assignStudent(
                recordId,
                studentId
        );

        return new MessageResponse(
                "Student " + studentId +
                        " assigned to admission record "
                        + recordId
        );
    }
}

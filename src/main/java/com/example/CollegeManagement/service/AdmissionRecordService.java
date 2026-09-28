package com.example.CollegeManagement.service;

import com.example.CollegeManagement.dto.AdmissionRecordRequest;
import com.example.CollegeManagement.dto.AdmissionRecordResponse;
import com.example.CollegeManagement.entity.AdmissionRecord;
import com.example.CollegeManagement.entity.Student;
import com.example.CollegeManagement.exception.ResourceNotFoundException;
import com.example.CollegeManagement.repository.AdmissionRecordRepository;
import com.example.CollegeManagement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository
            admissionRecordRepository;

    private final StudentRepository studentRepository;

    public AdmissionRecordService(
            AdmissionRecordRepository admissionRecordRepository,
            StudentRepository studentRepository) {

        this.admissionRecordRepository =
                admissionRecordRepository;

        this.studentRepository =
                studentRepository;
    }

    @Transactional
    public AdmissionRecordResponse create(
            AdmissionRecordRequest request) {

        AdmissionRecord record =
                new AdmissionRecord(
                        request.fees()
                );

        AdmissionRecord saved =
                admissionRecordRepository.save(record);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<AdmissionRecordResponse>
    findAll() {

        return admissionRecordRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdmissionRecordResponse findById(
            Long id) {

        return toResponse(getRecord(id));
    }

    @Transactional
    public void delete(Long id) {

        if (!admissionRecordRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Admission record not found: " + id
            );
        }

        admissionRecordRepository.deleteById(id);
    }

    @Transactional
    public void assignStudent(
            Long recordId,
            Long studentId) {

        AdmissionRecord record =
                getRecord(recordId);

        Student student =
                studentRepository.findById(studentId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Student not found: "
                                                + studentId
                                )
                        );

        record.setStudent(student);

        admissionRecordRepository.save(record);
    }

    private AdmissionRecord getRecord(Long id) {

        return admissionRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Admission record not found: "
                                        + id
                        )
                );
    }

    private AdmissionRecordResponse
    toResponse(AdmissionRecord record) {

        return new AdmissionRecordResponse(
                record.getId(),
                record.getFees(),
                record.getStudent() == null
                        ? null
                        : record.getStudent().getId()
        );
    }
}

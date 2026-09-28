package com.example.CollegeManagement.service;

import com.example.CollegeManagement.dto.IdNameResponse;
import com.example.CollegeManagement.dto.IdTitleResponse;
import com.example.CollegeManagement.dto.SubjectRequest;

import com.example.CollegeManagement.entity.Subject;

import com.example.CollegeManagement.exception.ResourceNotFoundException;

import com.example.CollegeManagement.repository.SubjectRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(
            SubjectRepository subjectRepository) {

        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public IdTitleResponse create(
            SubjectRequest request) {

        Subject subject =
                new Subject(request.title());

        Subject saved =
                subjectRepository.save(subject);

        return new IdTitleResponse(
                saved.getId(),
                saved.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<IdTitleResponse> findAll() {

        return subjectRepository.findAll()
                .stream()
                .map(subject ->
                        new IdTitleResponse(
                                subject.getId(),
                                subject.getTitle()
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public IdTitleResponse findById(Long id) {

        Subject subject =
                getSubject(id);

        return new IdTitleResponse(
                subject.getId(),
                subject.getTitle()
        );
    }

    @Transactional
    public void delete(Long id) {

        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Subject not found: " + id
            );
        }

        subjectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public IdNameResponse getProfessor(
            Long subjectId) {

        Subject subject =
                getSubject(subjectId);

        if (subject.getProfessor() == null) {
            throw new ResourceNotFoundException(
                    "No professor assigned to subject "
                            + subjectId
            );
        }

        return new IdNameResponse(
                subject.getProfessor().getId(),
                subject.getProfessor().getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<IdNameResponse>
    getStudents(Long subjectId) {

        Subject subject =
                getSubject(subjectId);

        return subject.getStudents()
                .stream()
                .map(student ->
                        new IdNameResponse(
                                student.getId(),
                                student.getName()
                        )
                )
                .toList();
    }

    private Subject getSubject(Long id) {

        return subjectRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found: " + id
                        )
                );
    }
}

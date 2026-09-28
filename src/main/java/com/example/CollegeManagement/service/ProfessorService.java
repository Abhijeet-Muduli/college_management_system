package com.example.CollegeManagement.service;

import com.example.CollegeManagement.dto.IdNameResponse;
import com.example.CollegeManagement.dto.IdTitleResponse;
import com.example.CollegeManagement.dto.ProfessorRequest;
import com.example.CollegeManagement.entity.Professor;
import com.example.CollegeManagement.entity.Student;
import com.example.CollegeManagement.entity.Subject;
import com.example.CollegeManagement.exception.ResourceNotFoundException;
import com.example.CollegeManagement.repository.ProfessorRepository;
import com.example.CollegeManagement.repository.StudentRepository;
import com.example.CollegeManagement.repository.SubjectRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public ProfessorService(
            ProfessorRepository professorRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository){
        this.professorRepository=professorRepository;
        this.studentRepository=studentRepository;
        this.subjectRepository=subjectRepository;
    }
    @Transactional
    public IdTitleResponse create(
            ProfessorRequest request){
        Professor professor=new Professor(request.title());
        Professor saved=professorRepository.save(professor);

        return new IdTitleResponse(
                saved.getId(),
                saved.getTitle()
        );
    }
    @Transactional(readOnly=true)
    public List<IdTitleResponse> findAll(){
        return professorRepository.findAll()
                .stream()
                .map(p ->
                        new IdTitleResponse(
                                p.getId(),
                                p.getTitle()
                        ))
                .toList();
    }
    @Transactional(readOnly=true)
    public IdTitleResponse findById(Long id){
        Professor professor=getProfessor(id);

        return new IdTitleResponse(
                professor.getId(),
                professor.getTitle()
                );
    }
    @Transactional
    public void delete(Long id){
        if(!professorRepository.existsById(id)){
            throw new ResourceNotFoundException(
                    "Professor not found: "+ id
            );
        }
        professorRepository.deleteById(id);
    }
    @Transactional
    public void assignSubject(
            Long professorId,
            Long subjectId
    ){
        Professor professor=getProfessor(professorId);
        Subject subject=subjectRepository.findById(subjectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subject not found: "+subjectId
                        ));
        subject.setProfessor(professor);
        subjectRepository.save(subject);
    }
    @Transactional
    public void assignStudent(
            Long professorId,
            Long studentId){
        Professor professor=getProfessor(professorId);
        Student student=studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found: "+studentId
                        )
                );
        student.addProfessor(professor);
        studentRepository.save(student);
    }
    @Transactional(readOnly =true)
    public List<IdTitleResponse> getSubjects(Long professorId){
        Professor professor=getProfessor(professorId);
        return professor.getSubjects()
                .stream()
                .map(subject ->
                        new IdTitleResponse(
                                subject.getId(),
                                subject.getTitle()
                        ))
                .toList();
    }
    @Transactional(readOnly=true)
    public List<IdNameResponse> getStudents(Long professorId){
        Professor professor=getProfessor(professorId);
        return professor.getStudents()
                .stream()
                .map(student ->
                        new IdNameResponse(
                                student.getId(),
                                student.getName()
                        ))
                .toList();
    }
    private Professor getProfessor(Long id){
        return professorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Professor not found: "+id
                        )
                );
    }


}

package com.example.CollegeManagement.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name="student_professor",
            joinColumns =
                    @JoinColumn(name="student_id"),
            inverseJoinColumns =
                    @JoinColumn(name="professor_id")
    )
    private List<Professor> professors=new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name="student_subject",

            joinColumns =
                    @JoinColumn(name="student_id"),
            inverseJoinColumns=
                    @JoinColumn(name="subject_id")
    )
    private List<Subject> subjects=new ArrayList<>();

    public Student(){

    }
    public Student(String name){
        this.name=name;
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public List<Professor> getProfessors(){
        return professors;
    }
    public List<Subject> getSubjects(){
        return subjects;
    }
    public void addProfessor(Professor professor){
        if(!professors.contains(professor)){
            professors.add(professor);
        }
        if(!professor.getStudents().contains(this)){
            professor.getStudents().add(this);
        }
    }
    public void addSubject(Subject subject){
        if(!subjects.contains(subject)){
            subjects.add(subject);
        }
        if(!subject.getStudents().contains(this)){
            subject.getStudents().add(this);
        }
    }
}

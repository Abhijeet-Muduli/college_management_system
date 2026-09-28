package com.example.CollegeManagement.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(
            mappedBy = "professor",
            cascade = CascadeType.ALL
    )
    private List<Subject> subjects=new ArrayList<>();

    @ManyToMany(mappedBy = "professors")
    private List<Student> students=new ArrayList<>();

    public Professor(){

    }
    public Professor(String title){
        this.title=title;
    }
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public List<Subject> getSubjects(){
        return subjects;
    }
    public List<Student> getStudents(){
        return students;
    }
    public void addSubject(Subject subject){
        if(!subjects.contains(subject)){
            subjects.add(subject);
        }
        subject.setProfessor(this);
    }
    public void addStudent(Student student){
        if(!students.contains(student)){
            students.add(student);
        }
        if(!student.getProfessors().contains(this)){
            student.getProfessors().add(this);
        }
    }
}

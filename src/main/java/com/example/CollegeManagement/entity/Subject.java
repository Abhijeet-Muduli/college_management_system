package com.example.CollegeManagement.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students=new ArrayList<>();
    protected Subject() {}
    public Subject(String title){
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
    public Professor getProfessor(){
        return professor;
    }
    public void setProfessor(Professor professor){
        this.professor=professor;
        if(professor != null &&
               !professor.getSubjects().contains(this)){
            professor.getSubjects().add(this);
        }
    }
    public List<Student> getStudents(){
        return students;
    }
}


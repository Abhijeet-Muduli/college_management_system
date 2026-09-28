package com.example.CollegeManagement.entity;
import jakarta.persistence.*;

@Entity
@Table(name="admission_records")
public class AdmissionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer fees;

    @OneToOne
    @JoinColumn(
            name="student_id",
            unique = true
    )
    private Student student;

    public AdmissionRecord(){

    }
    public AdmissionRecord(Integer fees){
        this.fees=fees;
    }
    public Long getId(){
        return id;
    }
    public Integer getFees(){
        return fees;
    }
    public void setFees(Integer fees){
        this.fees=fees;
    }
    public Student getStudent(){
        return student;
    }
    public void setStudent(Student student){
        this.student=student;
    }
}

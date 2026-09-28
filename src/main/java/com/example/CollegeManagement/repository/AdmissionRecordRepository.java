package com.example.CollegeManagement.repository;

import com.example.CollegeManagement.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository
        extends JpaRepository<AdmissionRecord, Long> {
}
package com.example.CollegeManagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record AdmissionRecordRequest (
       @NotNull(message="fees is required")
       @PositiveOrZero(message="fees cannot be negative")
       Integer fees
){
}

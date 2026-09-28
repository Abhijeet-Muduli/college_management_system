package com.example.CollegeManagement.dto;

import jakarta.validation.constraints.NotBlank;

public record SubjectRequest (
        @NotBlank(message = "title is required")
        String title
){
}

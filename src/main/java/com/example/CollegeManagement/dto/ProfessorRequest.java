package com.example.CollegeManagement.dto;

import jakarta.validation.constraints.NotBlank;

public record ProfessorRequest (
    @NotBlank(message = "title is required")
    String title)
{}

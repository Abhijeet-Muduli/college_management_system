package com.example.CollegeManagement.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentRequest(
        @NotBlank(message="name is required")
        String name
) {
}

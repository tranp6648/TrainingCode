package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public class CategoryDTO {
    @NotBlank(message = "Category cannot be empty")
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

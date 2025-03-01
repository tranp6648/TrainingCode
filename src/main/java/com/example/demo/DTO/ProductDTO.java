package com.example.demo.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private BigDecimal price;
    private String description;
    @NotNull(message = "Category ID is required")
    @Min(value = 1, message = "Category ID must be greater than zero")
    private int category_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}

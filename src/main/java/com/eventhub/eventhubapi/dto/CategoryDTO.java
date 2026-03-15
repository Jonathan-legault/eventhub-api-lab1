package com.eventhub.eventhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * DTO used for transferring category data between
 * the API and the client.
 * It contains basic category information such as id and name.
 */
public class CategoryDTO {

    // unique identifier for the category
    private Long id;

    // category name with validation rules
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;

    // default constructor
    public CategoryDTO() {
    }

    // constructor used when creating a DTO with values
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter for category id
    public Long getId() {
        return id;
    }

    // setter for category id
    public void setId(Long id) {
        this.id = id;
    }

    // getter for category name
    public String getName() {
        return name;
    }

    // setter for category name
    public void setName(String name) {
        this.name = name;
    }
}
package com.eventhub.eventhubapi.model;

/*
 * Model class representing an event category.
 * This class holds basic category data used by the application.
 */
public class Category {

    // unique identifier for the category
    private Long id;

    // name of the category
    private String name;

    // default constructor
    public Category() {
    }

    // constructor used to create a category with values
    public Category(Long id, String name) {
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
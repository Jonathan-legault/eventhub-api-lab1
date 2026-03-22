package com.eventhub.eventhubapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO used when creating a new registration.
 * Contains the user ID and a list of registration items.
 */
public class CreateRegistrationDTO {

    /**
     * ID of the user creating the registration.
     */
    @NotNull(message = "User ID is required")
    private Long userId;

    /**
     * List of items included in the registration.
     * Must contain at least one item, and each item is validated.
     */
    @Valid
    @NotEmpty(message = "At least one registration item is required")
    private List<CreateRegistrationItemDTO> items;

    /**
     * Default constructor required for JSON deserialization.
     */
    public CreateRegistrationDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CreateRegistrationItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CreateRegistrationItemDTO> items) {
        this.items = items;
    }
}
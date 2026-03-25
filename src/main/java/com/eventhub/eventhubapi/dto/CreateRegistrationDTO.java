package com.eventhub.eventhubapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * DTO used when creating a new registration.
 * Contains a list of registration items.
 * The authenticated user is taken from the JWT/security context.
 */
public class CreateRegistrationDTO {

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

    public List<CreateRegistrationItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CreateRegistrationItemDTO> items) {
        this.items = items;
    }
}
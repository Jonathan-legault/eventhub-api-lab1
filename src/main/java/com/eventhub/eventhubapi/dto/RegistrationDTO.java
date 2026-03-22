package com.eventhub.eventhubapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO used when returning registration data to the client.
 * Represents a registration and its associated items.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {

    /**
     * Unique identifier of the registration.
     */
    private Long id;

    /**
     * ID of the user who created the registration.
     */
    private Long userId;

    /**
     * Date and time when the registration was created.
     */
    private LocalDateTime registrationDate;

    /**
     * List of items included in the registration.
     */
    private List<RegistrationItemDTO> items;

    /**
     * Default constructor required for JSON serialization/deserialization.
     */
    public RegistrationDTO() {
    }

    /**
     * Constructor used to map registration data into the DTO.
     */
    public RegistrationDTO(Long id,
                           Long userId,
                           LocalDateTime registrationDate,
                           List<RegistrationItemDTO> items) {
        this.id = id;
        this.userId = userId;
        this.registrationDate = registrationDate;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public List<RegistrationItemDTO> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setItems(List<RegistrationItemDTO> items) {
        this.items = items;
    }
}
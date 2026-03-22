package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CreateRegistrationDTO;
import com.eventhub.eventhubapi.dto.RegistrationDTO;
import com.eventhub.eventhubapi.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/*
 * REST controller that handles API requests related to registrations.
 */
@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    /*
     * POST /api/v1/registrations
     * Creates a new registration.
     */
    @PostMapping
    public RegistrationDTO create(@Valid @RequestBody CreateRegistrationDTO dto) {
        return service.createRegistration(dto);
    }

    /*
     * GET /api/v1/registrations/{id}
     * Returns a registration by ID.
     */
    @GetMapping("/{id}")
    public RegistrationDTO getById(@PathVariable Long id) {
        return service.getRegistrationById(id);
    }

    /*
     * GET /api/v1/registrations/user/{userId}
     * Returns all registrations for a specific user.
     */
    @GetMapping("/user/{userId}")
    public List<RegistrationDTO> getByUser(@PathVariable Long userId) {
        return service.getRegistrationsByUser(userId);
    }

    /*
     * GET /api/v1/registrations/by-date?start=...&end=...
     * Returns registrations within a date range.
     */
    @GetMapping("/by-date")
    public List<RegistrationDTO> getByDateRange(@RequestParam LocalDateTime start,
                                                @RequestParam LocalDateTime end) {
        return service.getRegistrationsByDateRange(start, end);
    }
}
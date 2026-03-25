package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.CreateRegistrationDTO;
import com.eventhub.eventhubapi.dto.RegistrationDTO;
import com.eventhub.eventhubapi.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
     * USER or ADMIN can create a registration.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public RegistrationDTO create(@Valid @RequestBody CreateRegistrationDTO dto) {
        return service.createRegistration(dto);
    }

    /*
     * GET /api/v1/registrations/{id}
     * ADMIN only for now.
     * Safer than allowing arbitrary ID lookup for normal users.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public RegistrationDTO getById(@PathVariable Long id) {
        return service.getRegistrationById(id);
    }

    /*
     * GET /api/v1/registrations/my
     * Returns registrations for the currently logged-in user.
     */
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/my")
    public List<RegistrationDTO> getMyRegistrations(Authentication authentication) {
        return service.getRegistrationsByUsername(authentication.getName());
    }

    /*
     * GET /api/v1/registrations/user/{userId}
     * ADMIN only.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public List<RegistrationDTO> getByUser(@PathVariable Long userId) {
        return service.getRegistrationsByUser(userId);
    }

    /*
     * GET /api/v1/registrations/by-date?start=...&end=...
     * ADMIN only.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-date")
    public List<RegistrationDTO> getByDateRange(@RequestParam LocalDateTime start,
                                                @RequestParam LocalDateTime end) {
        return service.getRegistrationsByDateRange(start, end);
    }
}
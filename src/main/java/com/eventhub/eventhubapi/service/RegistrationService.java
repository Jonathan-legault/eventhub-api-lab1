package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.dto.CreateRegistrationDTO;
import com.eventhub.eventhubapi.dto.RegistrationDTO;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Service interface for registration-related business logic.
 */
public interface RegistrationService {

    RegistrationDTO createRegistration(CreateRegistrationDTO dto);

    RegistrationDTO getRegistrationById(Long id);

    List<RegistrationDTO> getRegistrationsByUser(Long userId);

    List<RegistrationDTO> getRegistrationsByUsername(String username);

    List<RegistrationDTO> getRegistrationsByDateRange(LocalDateTime start, LocalDateTime end);
}
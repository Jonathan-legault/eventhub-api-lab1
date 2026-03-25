package com.eventhub.eventhubapi.service.impl;

import com.eventhub.eventhubapi.dto.CreateRegistrationDTO;
import com.eventhub.eventhubapi.dto.CreateRegistrationItemDTO;
import com.eventhub.eventhubapi.dto.RegistrationDTO;
import com.eventhub.eventhubapi.dto.RegistrationItemDTO;
import com.eventhub.eventhubapi.model.Event;
import com.eventhub.eventhubapi.model.Registration;
import com.eventhub.eventhubapi.model.RegistrationItem;
import com.eventhub.eventhubapi.model.User;
import com.eventhub.eventhubapi.repository.EventRepository;
import com.eventhub.eventhubapi.repository.RegistrationItemRepository;
import com.eventhub.eventhubapi.repository.RegistrationRepository;
import com.eventhub.eventhubapi.repository.UserRepository;
import com.eventhub.eventhubapi.service.RegistrationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Service implementation for registration-related business logic.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationItemRepository registrationItemRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository,
                                   RegistrationItemRepository registrationItemRepository,
                                   UserRepository userRepository,
                                   EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.registrationItemRepository = registrationItemRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    /*
     * Converts a RegistrationItem entity to a RegistrationItemDTO.
     */
    private RegistrationItemDTO toItemDTO(RegistrationItem item) {
        return new RegistrationItemDTO(
                item.getEvent().getId(),
                item.getEvent().getName(),
                item.getQuantity()
        );
    }

    /*
     * Converts a Registration entity to a RegistrationDTO.
     */
    private RegistrationDTO toDTO(Registration registration) {
        List<RegistrationItemDTO> items = registration.getItems()
                .stream()
                .map(this::toItemDTO)
                .toList();

        return new RegistrationDTO(
                registration.getId(),
                registration.getUser().getId(),
                registration.getRegistrationDate(),
                items
        );
    }

    /*
     * Creates a new registration and its items.
     */
    @Override
    public RegistrationDTO createRegistration(CreateRegistrationDTO dto) {

        String username = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found: " + username));

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setRegistrationDate(LocalDateTime.now());

        Registration savedRegistration = registrationRepository.save(registration);

        List<RegistrationItem> savedItems = new java.util.ArrayList<>();

        for (CreateRegistrationItemDTO itemDTO : dto.getItems()) {
            Event event = eventRepository.findById(itemDTO.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found with id: " + itemDTO.getEventId()));

            RegistrationItem item = new RegistrationItem();
            item.setRegistration(savedRegistration);
            item.setEvent(event);
            item.setQuantity(itemDTO.getQuantity());

            RegistrationItem savedItem = registrationItemRepository.save(item);
            savedItems.add(savedItem);
        }

        savedRegistration.setItems(savedItems);

        return toDTO(savedRegistration);
    }

    /*
     * Returns a registration by ID.
     */
    @Override
    public RegistrationDTO getRegistrationById(Long id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));

        return toDTO(registration);
    }

    /*
     * Returns all registrations for a specific user.
     */
    @Override
    public List<RegistrationDTO> getRegistrationsByUser(Long userId) {
        return registrationRepository.findByUser_Id(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /*
     * Returns registrations within a date range.
     */
    @Override
    public List<RegistrationDTO> getRegistrationsByDateRange(LocalDateTime start, LocalDateTime end) {
        return registrationRepository.findByRegistrationDateBetween(start, end)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<RegistrationDTO> getRegistrationsByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return registrationRepository.findByUser_Id(user.getId())
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entity.
 * Provides CRUD operations and lookup methods for authentication and validation.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username (case-insensitive).
     *
     * @param username username
     * @return optional user
     */
    Optional<User> findByUsernameIgnoreCase(String username);

    /**
     * Finds a user by email (case-insensitive).
     *
     * @param email email address
     * @return optional user
     */
    Optional<User> findByEmailIgnoreCase(String email);
}
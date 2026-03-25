package com.eventhub.eventhubapi.repository;

import com.eventhub.eventhubapi.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    Optional<PasswordResetToken> findByUser_Id(Long userId);

    void deleteByUser_Id(Long userId);
}
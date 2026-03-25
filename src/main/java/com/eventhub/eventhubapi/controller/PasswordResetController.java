package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.PasswordResetDTO;
import com.eventhub.eventhubapi.dto.PasswordResetRequestDTO;
import com.eventhub.eventhubapi.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/request-reset")
    public Map<String, String> requestReset(@Valid @RequestBody PasswordResetRequestDTO dto) {
        String token = passwordResetService.createResetToken(dto.getEmail());

        return Map.of(
                "message", "Password reset token generated successfully",
                "token", token
        );
    }

    @PostMapping("/reset-password")
    public Map<String, String> resetPassword(@Valid @RequestBody PasswordResetDTO dto) {
        passwordResetService.resetPassword(dto.getToken(), dto.getNewPassword());

        return Map.of("message", "Password reset successfully");
    }
}
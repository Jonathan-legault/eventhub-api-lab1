package com.eventhub.eventhubapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for resetting a password using a token.
 */
public class PasswordResetDTO {

    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "New password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String newPassword;

    public PasswordResetDTO() {
    }

    public String getToken() {
        return token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
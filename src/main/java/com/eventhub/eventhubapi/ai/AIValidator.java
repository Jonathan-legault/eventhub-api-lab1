package com.eventhub.eventhubapi.ai;

import java.util.List;
import java.util.Locale;

public final class AIValidator {

    private AIValidator() {
    }

    private static final List<String> BLOCKED_PATTERNS = List.of(
            "api key",
            "apikey",
            "system prompt",
            "hidden prompt",
            "developer message",
            "internal configuration",
            "secret token",
            "authorization:",
            "bearer ",
            "ignore previous instructions"
    );

    public static String validateTextOrFallback(String output) {
        if (output == null || output.isBlank()) {
            return safeTextFallback();
        }

        String lower = output.toLowerCase(Locale.ROOT);

        for (String pattern : BLOCKED_PATTERNS) {
            if (lower.contains(pattern)) {
                return safeTextFallback();
            }
        }

        return output;
    }

    public static <T> T validateObjectOrFallback(T output, T fallback) {
        if (output == null) {
            return fallback;
        }

        String text = output.toString().toLowerCase(Locale.ROOT);

        for (String pattern : BLOCKED_PATTERNS) {
            if (text.contains(pattern)) {
                return fallback;
            }
        }

        return output;
    }

    private static String safeTextFallback() {
        return "Unable to generate a safe response for that request.";
    }
}
package com.eventhub.eventhubapi.ai;

public final class AISanitizer {

    private AISanitizer() {
    }

    public static String sanitize(String input) {
        if (input == null) return "";

        return input
                // Emails
                .replaceAll("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b", "[REDACTED_EMAIL]")

                // Phone numbers (North America)
                .replaceAll("\\b(?:\\+1[-.\\s]?)?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}\\b", "[REDACTED_PHONE]")

                // SIN (Canada)
                .replaceAll("\\b\\d{3}[- ]?\\d{3}[- ]?\\d{3}\\b", "[REDACTED_SIN]");
    }
}
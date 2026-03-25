package com.eventhub.eventhubapi.service;

import com.eventhub.eventhubapi.ai.AIPrompts;
import com.eventhub.eventhubapi.ai.AISanitizer;
import com.eventhub.eventhubapi.dto.*;
import com.eventhub.eventhubapi.dto.ai.EventTagSuggestionResponse;
import com.eventhub.eventhubapi.dto.ai.ScheduleItemResponse;
import com.eventhub.eventhubapi.dto.ai.StructuredEventDescriptionResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.eventhub.eventhubapi.ai.AIValidator;

@Service
public class AIService {

    private final ChatClient chatClient;

    public AIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String ask(String prompt) {
        String result = chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user(AISanitizer.sanitize(prompt))
                .call()
                .content();

        return AIValidator.validateTextOrFallback(result);
    }

    public String healthCheck() {
        return chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user("Say OK")
                .call()
                .content();
    }

    public String generateEventDescription(EventDescriptionRequest req) {

        String prompt = """
            Generate exactly one professional marketing event description.

            Event Name: %s
            Category: %s
            Location: %s
            Date: %s
            Keywords: %s

            Rules:
            - Return only one final description
            - Do not provide multiple options
            - Do not include headings
            - Do not include bullet points
            - Do not explain your choices
            - Keep the response between 120 and 180 words
            - Write in a polished promotional tone

            Output only the description text.
            """.formatted(
                AISanitizer.sanitize(req.getName()),
                AISanitizer.sanitize(req.getCategory()),
                AISanitizer.sanitize(req.getLocation()),
                AISanitizer.sanitize(req.getDate()),
                AISanitizer.sanitize(req.getKeywords())
        );

        String result = chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .content();

        return AIValidator.validateTextOrFallback(result);
    }

    public StructuredEventDescriptionResponse generateStructuredEventDescription(EventDescriptionRequest req) {

        String prompt = """
            Generate a structured event promotion object.

            Event Name: %s
            Category: %s
            Location: %s
            Date: %s
            Keywords: %s

            Return fields:
            - title
            - description
            - highlights (3 short phrases)
            - targetAudience
            - estimatedAttendance (integer only)

            Return only structured data.
            """.formatted(
                AISanitizer.sanitize(req.getName()),
                AISanitizer.sanitize(req.getCategory()),
                AISanitizer.sanitize(req.getLocation()),
                AISanitizer.sanitize(req.getDate()),
                AISanitizer.sanitize(req.getKeywords())
        );

        StructuredEventDescriptionResponse result = chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user("""
                    Example Input:
                    Event Name: Startup Launch Night
                    Category: Business
                    Location: Calgary
                    Date: 2026-05-10
                    Keywords: entrepreneurship, investors, networking

                    Example Output:
                    {
                      "title": "Startup Launch Night: Ideas to Impact",
                      "description": "Join Calgary’s emerging founders, investors, and innovators for Startup Launch Night, a dynamic event focused on entrepreneurship, new ventures, and meaningful business connections. Attendees will gain insights from startup leaders, explore growth opportunities, and build relationships that support future collaboration and investment.",
                      "highlights": [
                        "Founder networking",
                        "Investor insights",
                        "Startup growth strategies"
                      ],
                      "targetAudience": "Entrepreneurs, startup teams, investors, and business professionals interested in innovation and venture growth.",
                      "estimatedAttendance": 250
                    }
                    """)
                .user("""
                    Example Input:
                    Event Name: Green Future Expo
                    Category: Sustainability
                    Location: Edmonton
                    Date: 2026-09-18
                    Keywords: clean energy, sustainability, climate innovation

                    Example Output:
                    {
                      "title": "Green Future Expo: Innovating for Tomorrow",
                      "description": "Green Future Expo brings together sustainability leaders, innovators, and community stakeholders to explore practical solutions in clean energy and climate innovation. This event highlights emerging technologies, collaborative action, and forward-thinking strategies designed to build a more sustainable future.",
                      "highlights": [
                        "Clean energy trends",
                        "Sustainability innovation",
                        "Cross-sector collaboration"
                      ],
                      "targetAudience": "Environmental professionals, policymakers, students, innovators, and organizations focused on sustainable development.",
                      "estimatedAttendance": 600
                    }
                    """)
                .user(prompt)
                .call()
                .entity(StructuredEventDescriptionResponse.class);

        return AIValidator.validateObjectOrFallback(result, fallbackStructuredDescription());
    }

    public EventTagSuggestionResponse suggestEventTags(EventDescriptionRequest req) {

        String prompt = """
            Suggest relevant event tags.

            Event Name: %s
            Category: %s
            Location: %s
            Date: %s
            Keywords: %s

            Requirements:
            - Return 5 to 8 short tags
            - Prefer topic/category tags
            - Avoid date-only tags
            - No explanations
            """.formatted(
                AISanitizer.sanitize(req.getName()),
                AISanitizer.sanitize(req.getCategory()),
                AISanitizer.sanitize(req.getLocation()),
                AISanitizer.sanitize(req.getDate()),
                AISanitizer.sanitize(req.getKeywords())
        );

        EventTagSuggestionResponse result = chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .entity(EventTagSuggestionResponse.class);

        return AIValidator.validateObjectOrFallback(result, fallbackTags());
    }

    public EventScheduleResponse generateEventSchedule(EventScheduleRequest req) {

        String prompt = """
            Generate a one-day event schedule.

            Event Name: %s
            Category: %s
            Location: %s
            Date: %s
            Audience: %s
            Number of Sessions: %d
            Themes: %s

            Requirements:
            - eventTitle: use event name
            - schedule: list of sessions
            - each session must include time, sessionTitle, sessionType
            - session types: Keynote, Panel, Workshop, Break, Networking
            """.formatted(
                AISanitizer.sanitize(req.getEventName()),
                AISanitizer.sanitize(req.getCategory()),
                AISanitizer.sanitize(req.getLocation()),
                AISanitizer.sanitize(req.getDate()),
                AISanitizer.sanitize(req.getAudience()),
                req.getNumberOfSessions(),
                AISanitizer.sanitize(req.getThemes())
        );

        EventScheduleResponse result = chatClient.prompt()
                .system(AIPrompts.SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .entity(EventScheduleResponse.class);

        return AIValidator.validateObjectOrFallback(result, fallbackSchedule(req.getEventName()));
    }

    private StructuredEventDescriptionResponse fallbackStructuredDescription() {
        StructuredEventDescriptionResponse fallback = new StructuredEventDescriptionResponse();
        fallback.setTitle("Event Promotion Unavailable");
        fallback.setDescription("A safe structured event description could not be generated.");
        fallback.setHighlights(java.util.List.of(
                "Professional event content",
                "Structured AI output",
                "Safe fallback response"
        ));
        fallback.setTargetAudience("General event attendees.");
        fallback.setEstimatedAttendance(100);
        return fallback;
    }

    private EventTagSuggestionResponse fallbackTags() {
        EventTagSuggestionResponse fallback = new EventTagSuggestionResponse();
        fallback.setTags(java.util.List.of(
                "Event",
                "Conference",
                "Networking",
                "Professional Development",
                "Technology"
        ));
        return fallback;
    }

    private EventScheduleResponse fallbackSchedule(String eventName) {
        EventScheduleResponse fallback = new EventScheduleResponse();
        fallback.setEventTitle(eventName == null || eventName.isBlank() ? "Event Schedule" : eventName);

        ScheduleItemResponse item1 = new ScheduleItemResponse();
        item1.setTime("09:00 - 10:00");
        item1.setSessionTitle("Opening Session");
        item1.setSessionType("Keynote");

        ScheduleItemResponse item2 = new ScheduleItemResponse();
        item2.setTime("10:15 - 11:15");
        item2.setSessionTitle("Featured Discussion");
        item2.setSessionType("Panel");

        ScheduleItemResponse item3 = new ScheduleItemResponse();
        item3.setTime("11:30 - 12:00");
        item3.setSessionTitle("Networking Break");
        item3.setSessionType("Networking");

        fallback.setSchedule(java.util.List.of(item1, item2, item3));
        return fallback;
    }
}
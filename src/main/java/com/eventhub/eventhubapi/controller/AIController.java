package com.eventhub.eventhubapi.controller;

import com.eventhub.eventhubapi.dto.EventDescriptionRequest;
import com.eventhub.eventhubapi.dto.EventScheduleRequest;
import com.eventhub.eventhubapi.dto.EventScheduleResponse;
import com.eventhub.eventhubapi.dto.ai.EventTagSuggestionResponse;
import com.eventhub.eventhubapi.dto.ai.StructuredEventDescriptionResponse;
import com.eventhub.eventhubapi.dto.ai.FaqLoadRequest;
import com.eventhub.eventhubapi.dto.ai.FaqQuestionRequest;
import com.eventhub.eventhubapi.dto.ai.FaqSearchRequest;
import com.eventhub.eventhubapi.dto.ai.FaqSearchResponse;
import com.eventhub.eventhubapi.dto.ai.RagAnswerResponse;
import com.eventhub.eventhubapi.service.AIFaqService;
import com.eventhub.eventhubapi.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;
    private final AIFaqService aiFaqService;

    public AIController(AIService aiService, AIFaqService aiFaqService) {
        this.aiService = aiService;
        this.aiFaqService = aiFaqService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("response", aiService.healthCheck());
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String prompt = body.getOrDefault("prompt", "");
        return Map.of("response", aiService.ask(prompt));
    }

    @GetMapping("/env-check")
    public Map<String, Object> envCheck() {
        String key = System.getenv("GEMINI_API_KEY");
        return Map.of(
                "present", key != null && !key.isBlank(),
                "length", key == null ? 0 : key.length()
        );
    }

    @PostMapping("/event-description")
    public Map<String, String> generateDescription(@RequestBody EventDescriptionRequest request) {
        String result = aiService.generateEventDescription(request);
        return Map.of("description", result);
    }

    @PostMapping("/event-description/structured")
    public StructuredEventDescriptionResponse generateStructuredDescription(
            @RequestBody EventDescriptionRequest request) {
        return aiService.generateStructuredEventDescription(request);
    }

    @PostMapping("/event-tags")
    public EventTagSuggestionResponse suggestTags(@RequestBody EventDescriptionRequest request) {
        return aiService.suggestEventTags(request);
    }

    @PostMapping("/event-schedule")
    public EventScheduleResponse generateSchedule(@RequestBody EventScheduleRequest request) {
        return aiService.generateEventSchedule(request);
    }

    @PostMapping("/faq/load")
    public Map<String, Object> loadFaqs(@RequestBody FaqLoadRequest request) {
        return aiFaqService.loadFaqs(request);
    }

    @PostMapping("/faq/search")
    public FaqSearchResponse searchFaqs(@RequestBody FaqSearchRequest request) {
        return aiFaqService.searchFaqs(request);
    }

    @PostMapping("/faq/ask")
    public RagAnswerResponse askFaq(@RequestBody FaqQuestionRequest request) {
        return aiFaqService.askFaq(request);
    }
}
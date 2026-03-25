package com.eventhub.eventhubapi.dto.ai;

import java.util.List;

public class EventTagSuggestionResponse {

    private List<String> tags;

    public EventTagSuggestionResponse() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "EventTagSuggestionResponse{" +
                "tags=" + tags +
                '}';
    }
}


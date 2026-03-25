package com.eventhub.eventhubapi.dto.ai;

import java.util.List;

public class FaqSearchResponse {

    private String message;
    private List<FaqEntry> results;

    public FaqSearchResponse() {
    }

    public FaqSearchResponse(String message, List<FaqEntry> results) {
        this.message = message;
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FaqEntry> getResults() {
        return results;
    }

    public void setResults(List<FaqEntry> results) {
        this.results = results;
    }
}
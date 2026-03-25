package com.eventhub.eventhubapi.dto.ai;

import java.util.List;

public class FaqLoadRequest {

    private List<FaqEntry> faqs;

    public FaqLoadRequest() {
    }

    public FaqLoadRequest(List<FaqEntry> faqs) {
        this.faqs = faqs;
    }

    public List<FaqEntry> getFaqs() {
        return faqs;
    }

    public void setFaqs(List<FaqEntry> faqs) {
        this.faqs = faqs;
    }
}
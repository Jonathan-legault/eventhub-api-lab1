package com.eventhub.eventhubapi.dto.ai;

import java.util.List;

public class RagAnswerResponse {

    private String question;
    private String answer;
    private List<FaqEntry> matchedFaqs;

    public RagAnswerResponse() {
    }

    public RagAnswerResponse(String question, String answer, List<FaqEntry> matchedFaqs) {
        this.question = question;
        this.answer = answer;
        this.matchedFaqs = matchedFaqs;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<FaqEntry> getMatchedFaqs() {
        return matchedFaqs;
    }

    public void setMatchedFaqs(List<FaqEntry> matchedFaqs) {
        this.matchedFaqs = matchedFaqs;
    }
}
package com.eventhub.eventhubapi.dto.ai;

public class FaqEntry {

    private String id;
    private String category;
    private String question;
    private String answer;

    public FaqEntry() {
    }

    public FaqEntry(String id, String category, String question, String answer) {
        this.id = id;
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {
        return "FaqEntry{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
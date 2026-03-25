package com.eventhub.eventhubapi.dto.ai;

public class FaqQuestionRequest {

    private String question;
    private int topK = 3;
    private double similarityThreshold = 0.6;

    public FaqQuestionRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTopK() {
        return topK;
    }

    public void setTopK(int topK) {
        this.topK = topK;
    }

    public double getSimilarityThreshold() {
        return similarityThreshold;
    }

    public void setSimilarityThreshold(double similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }
}
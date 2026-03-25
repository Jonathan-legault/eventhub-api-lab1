package com.eventhub.eventhubapi.dto.ai;

public class FaqSearchRequest {

    private String query;
    private int topK = 5;
    private double similarityThreshold = 0.6;

    public FaqSearchRequest() {
    }

    public FaqSearchRequest(String query, int topK, double similarityThreshold) {
        this.query = query;
        this.topK = topK;
        this.similarityThreshold = similarityThreshold;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
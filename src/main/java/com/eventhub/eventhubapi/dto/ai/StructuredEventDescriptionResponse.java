package com.eventhub.eventhubapi.dto.ai;

import java.util.List;

public class StructuredEventDescriptionResponse {

    private String title;
    private String description;
    private List<String> highlights;
    private String targetAudience;
    private int estimatedAttendance;

    public StructuredEventDescriptionResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public int getEstimatedAttendance() {
        return estimatedAttendance;
    }

    public void setEstimatedAttendance(int estimatedAttendance) {
        this.estimatedAttendance = estimatedAttendance;
    }

    @Override
    public String toString() {
        return "StructuredEventDescriptionResponse{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", highlights=" + highlights +
                ", targetAudience='" + targetAudience + '\'' +
                ", estimatedAttendance=" + estimatedAttendance +
                '}';
    }
}
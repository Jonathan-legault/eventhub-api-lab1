package com.eventhub.eventhubapi.dto;

import com.eventhub.eventhubapi.dto.ai.ScheduleItemResponse;

import java.util.List;

public class EventScheduleResponse {

    private String eventTitle;
    private List<ScheduleItemResponse> schedule;

    public EventScheduleResponse() {
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public List<ScheduleItemResponse> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItemResponse> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "EventScheduleResponse{" +
                "eventTitle='" + eventTitle + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
package com.eventhub.eventhubapi.dto.ai;

public class ScheduleItemResponse {

    private String time;
    private String sessionTitle;
    private String sessionType;

    public ScheduleItemResponse() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    @Override
    public String toString() {
        return "ScheduleItemResponse{" +
                "time='" + time + '\'' +
                ", sessionTitle='" + sessionTitle + '\'' +
                ", sessionType='" + sessionType + '\'' +
                '}';
    }
}
package com.eventhub.eventhubapi.exception;

/*
 * Custom exception used when an event cannot be found.
 * This is typically thrown when a request is made for an
 * event ID that does not exist in the database.
 */
public class EventNotFoundException extends RuntimeException {

    // constructor that passes the error message to the parent RuntimeException
    public EventNotFoundException(String message) {
        super(message);
    }
}
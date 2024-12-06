package com.example.demo.exception;


import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int statusCode;

    // Constructor
    public ApiError(LocalDateTime timestamp, String message, String details, int statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }


}

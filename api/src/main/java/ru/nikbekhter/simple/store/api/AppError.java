package ru.nikbekhter.simple.store.api;

public class AppError {
    private int statusCode;
    private String message;

    public AppError() {
    }

    public AppError(int value, String message) {
        this.statusCode = value;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

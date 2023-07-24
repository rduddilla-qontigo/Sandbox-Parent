package com.rduddilla.sandbox.filters.model.exceptions;

public class FilterException extends Exception {
    private String message;

    public FilterException(String message) {
        super(message);
    }
}

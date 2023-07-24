package com.rduddilla.sandbox.filters.model.enums;

public enum FilterOperation {
    CONTAINS("contains"),
    EQUALS("equals"),
    STARTS_WITH("starts_with"),
    ENDS_WITH("ends with");

    private String label;

    FilterOperation(String label) {
        this.label = label;
    }
}

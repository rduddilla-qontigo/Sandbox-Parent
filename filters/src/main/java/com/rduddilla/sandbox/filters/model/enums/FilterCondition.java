package com.rduddilla.sandbox.filters.model.enums;

public enum FilterCondition {
    OR("Match any"),
    AND("Match all");

    private String label;

    FilterCondition(String label) {
        this.label = label;
    }
}

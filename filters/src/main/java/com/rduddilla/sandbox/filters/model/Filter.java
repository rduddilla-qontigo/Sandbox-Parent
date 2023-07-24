package com.rduddilla.sandbox.filters.model;

import com.rduddilla.sandbox.filters.model.enums.FilterOperation;
import lombok.*;

@Builder
@Getter @Setter
@ToString
public class Filter<T> {
    private String name;
    private FilterOperation operation;
    private T value;
}

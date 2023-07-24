package com.rduddilla.sandbox.filters.model;

import com.rduddilla.sandbox.filters.model.enums.FilterCondition;
import com.rduddilla.sandbox.filters.model.exceptions.FilterException;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class FilterNode {

    public static final int MAX_NESTING = 3;
    public static final int MAX_FILTERS = 20;
    public static final int MAX_NESTED_FILTERS = 5;

    private FilterCondition condition;
    private List<Filter> filters;
    private List<FilterNode> nestedFilters;

    public FilterNode(FilterCondition condition) {
        this.condition = condition;
        filters = new ArrayList<>(MAX_FILTERS);
        nestedFilters = new ArrayList<>(MAX_FILTERS);
    }

    public void addFilter(Filter filter) throws FilterException {
        if(filters.size() < MAX_FILTERS) {
            filters.add(filter);
        } else {
            throw new FilterException("Cannot add filter: " + filter + " - Maximum filters limit(" + MAX_FILTERS + ") reached");
        }
    }

    public void addNestedFilter(FilterNode nestedFilter) throws FilterException {
        if(nestedFilters.size() < MAX_NESTED_FILTERS) {
            nestedFilters.add(nestedFilter);
        } else {
            throw new FilterException("Cannot add nestedFilter: " + nestedFilter + " - Maximum nestedFilters limit(" + MAX_NESTED_FILTERS + ") reached");
        }
    }
}

package com.rduddilla.sandbox.filters.controller;

import com.rduddilla.sandbox.filters.model.Filter;
import com.rduddilla.sandbox.filters.model.FilterNode;
import com.rduddilla.sandbox.filters.model.enums.FilterOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.rduddilla.sandbox.filters.model.enums.FilterCondition.AND;
import static com.rduddilla.sandbox.filters.model.enums.FilterCondition.OR;
import static com.rduddilla.sandbox.filters.model.enums.LongShort.LONG;
import static com.rduddilla.sandbox.filters.model.enums.LongShort.SHORT;

@RestController
@RequestMapping(value="api/v1/filters")
@Slf4j
public class FilterController {

    /*public FilterController() {
    }*/

    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public ResponseEntity<FilterNode> basicFilters() {
        try {
            return ResponseEntity.ok(getBasicFilter());
        } catch (Exception e) {
            log.error("Error: {}", e.fillInStackTrace());
        }
        return ResponseEntity.of(Optional.empty());
    }

    @RequestMapping(value = "/nested", method = RequestMethod.GET)
    public ResponseEntity<FilterNode> nestedFilters() {
        try {
            return ResponseEntity.ok(getNestedFilter());
        } catch (Exception e) {
            log.error("Error: {}", e.fillInStackTrace());
        }
        return ResponseEntity.of(Optional.empty());
    }

    @RequestMapping(value = "/complex", method = RequestMethod.GET)
    public ResponseEntity<FilterNode> complexFilters() {
        try {
            return ResponseEntity.ok(getComplexFilter());
        } catch (Exception e) {
            log.error("Error: {}", e.fillInStackTrace());
        }
        return ResponseEntity.of(Optional.empty());
    }

    private FilterNode getBasicFilter() throws Exception {
        Filter filter1 = Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.EQUALS)
                .value("Stock")
                .build();

        Filter filter2 = Filter.builder()
                .name("LongOrShort")
                .operation(FilterOperation.EQUALS)
                .value(LONG)
                .build();

        FilterNode filterNode = new FilterNode(AND);
        filterNode.addFilter(filter1);
        filterNode.addFilter(filter2);

        return filterNode;
    }

    private FilterNode getNestedFilter() throws Exception {
        Filter filter1 = Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.EQUALS)
                .value("Stock")
                .build();

        Filter filter2 = Filter.builder()
                .name("LongOrShort")
                .operation(FilterOperation.EQUALS)
                .value(LONG)
                .build();

        FilterNode filterNode1 = new FilterNode(AND);
        filterNode1.addFilter(filter1);
        filterNode1.addFilter(filter2);

        Filter filter3 = Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.EQUALS)
                .value("Swap")
                .build();

        Filter filter4 = Filter.builder()
                .name("LongOrShort")
                .operation(FilterOperation.EQUALS)
                .value(SHORT)
                .build();

        FilterNode filterNode2 = new FilterNode(AND);
        filterNode2.addFilter(filter3);
        filterNode2.addFilter(filter4);

        FilterNode filterNode = new FilterNode(OR);
        filterNode.addNestedFilter(filterNode1);
        filterNode.addNestedFilter(filterNode2);

        return filterNode;
    }

    private FilterNode getComplexFilter() throws Exception {
        Filter filter1 = Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.EQUALS)
                .value("Stock")
                .build();

        Filter filter2 = Filter.builder()
                .name("LongOrShort")
                .operation(FilterOperation.EQUALS)
                .value(LONG)
                .build();

        FilterNode filterNode1 = new FilterNode(AND);
        filterNode1.addFilter(filter1);
        filterNode1.addFilter(filter2);

        Filter filter3 = Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.EQUALS)
                .value("Swap")
                .build();

        Filter filter4 = Filter.builder()
                .name("LongOrShort")
                .operation(FilterOperation.EQUALS)
                .value(SHORT)
                .build();

        FilterNode filterNode2 = new FilterNode(AND);
        filterNode2.addFilter(filter3);
        filterNode2.addFilter(filter4);

        FilterNode filterNode = new FilterNode(OR);
        List<String> instrumentTypes = Arrays.asList(new String[]{"Swap", "Stock"});
        filterNode.addFilter(Filter.builder()
                .name("InstrumentType")
                .operation(FilterOperation.CONTAINS)
                .value(instrumentTypes)
                .build());
        filterNode.addNestedFilter(filterNode1);
        filterNode.addNestedFilter(filterNode2);

        return filterNode;
    }
}

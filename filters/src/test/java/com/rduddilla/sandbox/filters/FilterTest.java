package com.rduddilla.sandbox.filters;

import com.rduddilla.sandbox.filters.model.Filter;
import com.rduddilla.sandbox.filters.model.FilterNode;
import com.rduddilla.sandbox.filters.model.enums.FilterCondition;
import com.rduddilla.sandbox.filters.model.enums.FilterOperation;
import com.rduddilla.sandbox.filters.model.exceptions.FilterException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static com.rduddilla.sandbox.filters.FilterTest.LongShort.LONG;
import static com.rduddilla.sandbox.filters.FilterTest.LongShort.SHORT;
import static com.rduddilla.sandbox.filters.model.FilterNode.MAX_FILTERS;
import static com.rduddilla.sandbox.filters.model.FilterNode.MAX_NESTED_FILTERS;
import static com.rduddilla.sandbox.filters.model.enums.FilterCondition.AND;
import static com.rduddilla.sandbox.filters.model.enums.FilterCondition.OR;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class FilterTest {

    public enum LongShort {
        LONG, SHORT;
    }

    @Test
    public void testMaxFilters() throws Exception {
        FilterNode filterNode = new FilterNode(FilterCondition.AND);
        assertThrows(FilterException.class, () -> {
            for (int i = 0; i <= MAX_FILTERS; i++) {
                filterNode.addFilter(Filter.builder()
                        .name(random_string(8, true))
                        .operation(FilterOperation.EQUALS)
                        .value(random_string(12, false))
                        .build());
            }
        });
    }

    @Test
    public void testMaxNestedFilters() throws Exception {
        FilterNode filterNode = new FilterNode(AND);
        assertThrows(FilterException.class, () -> {
            for (int i = 0; i <= MAX_NESTED_FILTERS; i++) {
                filterNode.addNestedFilter(new FilterNode(AND));
            }
        });
    }
    @Test
    public void testSimpleFilter() throws Exception {
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

        log.info("Filter node: {}", filterNode);
    }

    @Test
    public void testNestedFilter() throws Exception {
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

        log.info("Filter node: {}", filterNode);
    }

    private String random_string(int length, boolean upper) {
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        String random = new String(array, Charset.forName("UTF-8"));
        return upper ? random.toUpperCase() : random;
    }
}

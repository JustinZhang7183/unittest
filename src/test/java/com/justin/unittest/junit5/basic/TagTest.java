package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
public class TagTest {
    @Test
    @Tag("taxes")
    void test_tax_calculation() {

    }
}

package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class AssumptionTest {
    @Test
    void if_true_then_do_something() {
        Assumptions.assumeTrue(1 == 2,"1 == 1");
        Assumptions.assumingThat(1 == 1, () -> Assertions.assertEquals(1,1));
    }
}

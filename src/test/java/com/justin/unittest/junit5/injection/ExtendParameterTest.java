package com.justin.unittest.junit5.injection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(RandomParametersExtension.class)
public class ExtendParameterTest {
    @Test
    void inject_integer(@Random int i, @Random int j) {
        Assertions.assertNotEquals(i, j);
    }

    @Test
    void inject_double(@Random double d, TestReporter reporter) {
        reporter.publishEntry(String.valueOf(d));
        Assertions.assertEquals(0.0, d, 1.0);
    }
}

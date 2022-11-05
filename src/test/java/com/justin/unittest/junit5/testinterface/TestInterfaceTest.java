package com.justin.unittest.junit5.testinterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInterfaceTest implements TestLifecycleLogger, TimeExecutionLogger, TestInterfaceDynamicTestsDemo {
    @Test
    void is_equal_value() {
        Assertions.assertEquals(1, "a".length(), "is always equal");
    }
}

package com.justin.unittest.junit5.testinterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * description: test class implemented those three interface.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 5:45 PM
 */
public class TestInterfaceTest implements TestLifecycleLogger, TimeExecutionLogger,
    TestInterfaceDynamicTestsDemo {
  @Test
  void is_equal_value() {
    Assertions.assertEquals(1, "a".length(),
        "is always equal");
  }
}

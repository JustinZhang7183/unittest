package com.justin.unittest.junit5.parameterizedtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Description: lifecycle and interoperability test case.
 * <p>you can use ParameterResolver extensions with @ParameterizedTest method.
 * but method parameters that are resolved by argument source need to come first
 * in the argument list</p>
 * <p>values from argument sources are not resolved for lifecycle methods
 * and test class constructors</p>
 *
 * @author Justin_Zhang
 * @date 11/9/2022 10:54
 */
public class LifecycleAndInteroperabilityTest {
  @BeforeEach
  void beforeEach(TestInfo testInfo) {
    // ...
  }

  @ParameterizedTest
  @ValueSource(strings = "apple")
  void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
    testReporter.publishEntry("argument", argument);
  }

  @AfterEach
  void afterEach(TestInfo testInfo) {
    // ...
  }
}

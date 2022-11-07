package com.justin.unittest.junit5.testinterface;

import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;

/**
 * description: junit jupiter allow some annotation to be declared on interface default method.
 * include
 * <p>
 * 1.@Test, @RepeatedTest, @ParameterizedTest
 * 2.@TestFactory, @TestTemplate, @BeforeEach, and @AfterEach
 * </p>
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:49 PM
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifecycleLogger {
  static final Logger logger = Logger.getLogger(TestLifecycleLogger.class.getName());

  @BeforeAll
  default void beforeAllTests() {
    logger.info("Before all tests");
  }

  @AfterAll
  default void afterAllTests() {
    logger.info("After all tests");
  }

  @BeforeEach
  default void beforeEachTest(TestInfo testInfo) {
    logger.info(() -> String.format("About to execute [%s]",
        testInfo.getDisplayName()));
  }

  @AfterEach
  default void afterEachTest(TestInfo testInfo) {
    logger.info(() -> String.format("Finished executing [%s]",
        testInfo.getDisplayName()));
  }
}

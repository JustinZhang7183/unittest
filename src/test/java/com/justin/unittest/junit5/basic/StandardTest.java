package com.justin.unittest.junit5.basic;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Description: a sample code of standard test.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 14:57
 */
@Slf4j
public class StandardTest {
  /**
   * description: the method which is annotated by @BeforeAll should be static,
   * unless there is a configuration in junit-platform.properties like:
   * junit.jupiter.testinstance.lifecycle.default = per_class
   * or there is a relevant annotation on class.
   */
  @BeforeAll
  static void initAll() {
    log.info("init all");
  }

  @BeforeEach
  void init() {
    log.info("init");
  }

  @Test
  void succeedingTest() {
  }

  @Test
  void failingTest() {
    fail("a failing test");
  }

  @Test
  @Disabled("for demonstration purposes")
  void skippedTest() {
    // not executed
  }

  @Test
  void abortedTest() {
    assumeTrue("abc".contains("Z"), "abc don't contains Z");
    fail("test should have been aborted");
  }

  @AfterEach
  void tearDown() {
    log.info("tear down");
  }

  @AfterAll
  void tearDownAll() {
    log.info("tear down all");
  }
}

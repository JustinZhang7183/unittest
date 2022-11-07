package com.justin.unittest.junit5.basic;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Description: test for test instance lifecycle.
 * <p>
 * Default setting is PER_METHOD.
 * If is this way, the method under @BeforeAll and @AfterAll should be static.
 * If is PER_CLASS, it no need be.
 * </p>
 * <p>
 * PER_METHOD way will create a new instance when each method execute.
 * PER_CLASS way will create only one.
 * </p>
 *
 * @author Justin_Zhang
 * @date 11/7/2022 14:38
 */
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class InstanceLifecycleTest {
  private final Integer random = new Random().nextInt();

  @BeforeAll
  public static void beforeAll() {
  }

  @Test
  public void show_random_value() {
    log.info(String.valueOf(random));
  }

  @Test
  public void show_random_value_two() {
    log.info(String.valueOf(random));
  }
}

package com.justin.unittest.junit5.basic;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * description: unit test of assertion util.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 3:48 PM
 */
@Slf4j
public class AssertionTest {
  @Test
  public void standard_assertions() {
    Assertions.assertEquals(1, 1, "1 is always equals 1");
    Assertions.assertTrue(3 > 1, () -> "3 is always greater than 1");
  }

  @Test
  public void dependent_assertions() {
    Assertions.assertAll("outside assertion",
        () -> {
          Assertions.assertNotNull(1);
        },
        () -> {
          Assertions.assertAll("inside assertion",
              () -> Assertions.assertEquals(1, 1),
              () -> Assertions.assertEquals(2, 2));
        });
  }

  @Test
  public void exception_assertions() {
    Assertions.assertThrows(ArithmeticException.class, () -> {
      throw new ArithmeticException();
    });
  }

  @Test
  public void timeout_assertions() {
    String str = Assertions.assertTimeout(Duration.ofSeconds(2), () -> "result");
    Assertions.assertTimeout(Duration.ofSeconds(2), () -> log.info("result"));
    Assertions.assertEquals(str, "result");
  }

  /**
   * description: this test will fail after 5 seconds.
   */
  @Test
  public void timeout_exceeded() {
    Assertions.assertTimeout(Duration.ofSeconds(1), () -> TimeUnit.SECONDS.sleep(5));
  }

  /**
   * description: this test will fail after 1 second because it executes by other thread.
   */
  @Test
  public void timeout_exceeded_with_preemptive_termination() {
    Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> TimeUnit.SECONDS.sleep(5));
  }
}

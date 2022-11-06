package com.justin.unittest.junit5.basic;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertionTest {
  @Test
  void if_time_out() {
    Assertions.assertTimeout(Duration.ofSeconds(2L), () -> TimeUnit.SECONDS.sleep(1L));
  }

  @Test
  void timeout_not_exceeded_with_result() {
    Integer result = Assertions.assertTimeout(Duration.ofSeconds(1L), () -> 1);
    Assertions.assertEquals(1, (int) result);
  }

  @Test
  void timeout_exceeded_with_preemptive_termination() {
    Assertions.assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
      // TODO
      new CountDownLatch(1).await();
    });
  }
}

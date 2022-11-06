package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

/**
 * description: assumptions test shows how to control test process.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 4:30 PM
 */
public class AssumptionTest {
  @Test
  void if_true_then_do_something() {
    Assumptions.assumeTrue(1 == 2, "1 == 1");
    Assumptions.assumeTrue(1 == 2, () -> "1 == 1");
    Assumptions.assumingThat(1 == 1, () -> Assertions.assertEquals(1, 1));
  }
}

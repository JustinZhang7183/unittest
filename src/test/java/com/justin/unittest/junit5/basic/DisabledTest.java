package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Description: the sample of disabled annotation using.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 16:33
 */
@Disabled("Disabled until bug #99 has been fixed")
public class DisabledTest {
  @Test
//  @Disabled
  void test_will_be_skipped() {
    Assertions.assertEquals(1, 3);
  }

  @Test
  void test_will_execute() {
    Assertions.assertEquals(1, 1);
  }
}

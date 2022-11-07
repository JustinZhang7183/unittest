package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * description: sample of using tag within junit jupiter.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 2:19 PM
 */
@Tag("fast")
@Tag("model")
public class TagTest {
  @Test
  @Tag("taxes")
  void test_tax_calculation() {

  }
}

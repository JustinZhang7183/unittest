package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * Description: execution order of test method and class test.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 14:22
 */
@Order(2)
public class OrderTwoTest {
  @Test
  public void test_class_order(){
  }
}

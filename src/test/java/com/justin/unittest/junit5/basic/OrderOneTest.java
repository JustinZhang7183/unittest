package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Description: execution order of test method and class test.
 * <p>
 * method
 * 1.MethodOrderer.DisplayName
 * 2.MethodOrderer.MethodName
 * 3.MethodOrderer.OrderAnnotation
 * 4.MethodOrderer.Random
 * 5.MethodOrderer.Alphanumeric
 * </p>
 * <p>
 * class
 * 1.ClassOrderer.DisplayName
 * 2.ClassOrderer.ClassName
 * 3.ClassOrderer.OrderAnnotation
 * 4.ClassOrderer.Random
 * </p>
 * alternative way is set up config in junit-platform.properties.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 14:21
 */
@Order(1)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class OrderOneTest {
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  @Nested
  @Order(2)
  class NumberTwoClass {
    @Test
    @Order(2)
    public void number_two_test(){
    }

    @Test
    @Order(1)
    public void number_one_test(){
    }

    @Test
    @Order(3)
    public void number_three_test(){
    }
  }

  @Order(1)
  @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
  @Nested
  class NumberOneClass {
    @Test
    @Order(2)
    public void number_two_test(){
    }

    @Test
    @Order(1)
    public void number_one_test(){
    }

    @Test
    @Order(3)
    public void number_three_test(){
    }
  }
}

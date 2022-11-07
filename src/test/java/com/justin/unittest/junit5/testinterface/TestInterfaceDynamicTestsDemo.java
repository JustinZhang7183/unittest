package com.justin.unittest.junit5.testinterface;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * description: the sample of dynamic test in default method within interface.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:53 PM
 */
public interface TestInterfaceDynamicTestsDemo {
  @TestFactory
  default Stream<DynamicTest> dynamicTestsForPalindromes() {
    return Stream.of("racecar", "radar", "mom", "dad")
        .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
  }

  /**
   * description: estimating a given text whether is palindrome.
   *
   * @param text text of input
   * @return Boolean
   */
  default Boolean isPalindrome(String text) {
    int startIndex = 0;
    int endIndex = text.length() - 1;
    while (startIndex < endIndex) {
      if (text.charAt(startIndex) != text.charAt(endIndex)) {
        return false;
      }
      startIndex++;
      endIndex--;
    }
    return true;
  }
}

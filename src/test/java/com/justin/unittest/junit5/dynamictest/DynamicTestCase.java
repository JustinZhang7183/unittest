package com.justin.unittest.junit5.dynamictest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.api.Named.named;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

/**
 * description: the sample of dynamic test case.
 * <p>@BeforeEach and @AfterEach methods and their corresponding extension
 * callbacks are executed for the @TestFactory method but not for
 * each dynamic test</p>
 *
 * @author Justin_Zhang
 * @date 11/9/2022 11:15 AM
 */
@Slf4j
public class DynamicTestCase {

  @BeforeEach
  public void beforeEach() {
    log.info("before @TestFactory method");
  }

  // This will result in a JUnitException!
  @TestFactory
  @Disabled("just for demonstrate")
  List<String> dynamicTestsWithInvalidReturnType() {
    return List.of("Hello");
  }

  @TestFactory
  Collection<DynamicTest> dynamicTestsFromCollection() {
    return Arrays.asList(
        dynamicTest("1st dynamic test", () -> assertTrue(1 == 1)),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, 4))
    );
  }

  @TestFactory
  Iterable<DynamicTest> dynamicTestsFromIterable() {
    return Arrays.asList(
        dynamicTest("1st dynamic test", () -> assertTrue(1 == 1)),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, 4))
    );
  }

  @TestFactory
  Iterator<DynamicTest> dynamicTestsFromIterator() {
    return Arrays.asList(
        dynamicTest("1st dynamic test", () -> assertTrue(1 == 1)),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, 4))
    ).iterator();
  }

  @TestFactory
  DynamicTest[] dynamicTestsFromArray() {
    return new DynamicTest[] {
        dynamicTest("1st dynamic test", () -> assertTrue(1 == 1)),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, 4))
    };
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromStream() {
    return Stream.of("racecar", "radar", "mom", "dad")
        .map(text -> dynamicTest(text, () -> assertTrue(text.length() > 1)));
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromIntStream() {
    // Generates tests for the first 10 even integers.
    return IntStream.iterate(0, n -> n + 2).limit(10)
        .mapToObj(n -> dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
  }

  @TestFactory
  Stream<DynamicTest> generateRandomNumberOfTestsFromIterator() {

    // Generates random positive integers between 0 and 100 until
    // a number evenly divisible by 7 is encountered.
    Iterator<Integer> inputGenerator = new Iterator<Integer>() {

      Random random = new Random();
      int current;

      @Override
      public boolean hasNext() {
        current = random.nextInt(100);
        return current % 7 != 0;
      }

      @Override
      public Integer next() {
        return current;
      }
    };

    // Generates display names like: input:5, input:37, input:85, etc.
    Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

    // Executes tests based on the current input value.
    ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

    // Returns a stream of dynamic tests.
    return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromStreamFactoryMethod() {
    // Stream of palindromes to check
    Stream<String> inputStream = Stream.of("racecar", "radar", "mom", "dad");

    // Generates display names like: racecar's length > 1
    Function<String, String> displayNameGenerator = text -> text + " length > 1";

    // Executes tests based on the current input value.
    ThrowingConsumer<String> testExecutor = text -> assertTrue(text.length() > 1);

    // Returns a stream of dynamic tests.
    return DynamicTest.stream(inputStream, displayNameGenerator, testExecutor);
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromStreamFactoryMethodWithNames() {
    // Stream of palindromes to check
    Stream<Named<String>> inputStream = Stream.of(
        named("racecar is a palindrome", "racecar"),
        named("radar is also a palindrome", "radar"),
        named("mom also seems to be a palindrome", "mom"),
        named("dad is yet another palindrome", "dad")
    );

    // Returns a stream of dynamic tests.
    return DynamicTest.stream(inputStream,
        text -> assertTrue(text.length() > 1));
  }

  @TestFactory
  Stream<DynamicNode> dynamicTestsWithContainers() {
    return Stream.of("A", "B", "C")
        .map(input -> dynamicContainer("Container " + input, Stream.of(
            dynamicTest("not null", () -> assertNotNull(input)),
            dynamicContainer("properties", Stream.of(
                dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
                dynamicTest("not empty", () -> assertFalse(input.isEmpty()))
            ))
        )));
  }

  @TestFactory
  DynamicNode dynamicNodeSingleTest() {
    return dynamicTest("'pop' length > 1", () -> assertTrue("pop".length() > 1));
  }

  @TestFactory
  DynamicNode dynamicNodeSingleContainer() {
    return dynamicContainer("length > 1",
        Stream.of("racecar", "radar", "mom", "dad")
            .map(text -> dynamicTest(text, () -> assertTrue(text.length() > 1))
            ));
  }

}
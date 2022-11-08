package com.justin.unittest.junit5.parameterizedtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * description: sample of parameterized test.
 *
 * @author Justin_Zhang
 * @date 11/8/2022 9:34 AM
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class ParameterizedTestCase {
  // what situation can we use autoCloseArguments = false?
  @ParameterizedTest(autoCloseArguments = false)
  @ValueSource(strings = {"a", "b"})
  void value_source_test(String str) {
    Assertions.assertTrue(str.isEmpty() || str.length() > 0);
  }

  @ParameterizedTest
  @NullSource
  @EmptySource
  @NullAndEmptySource
  void null_and_empty_test(String str) {
    Assertions.assertTrue(str == null || str.isEmpty());
  }

  @ParameterizedTest
  @EnumSource(UnitType.class)
  void enum_test(UnitTypeI type) {
    Assertions.assertTrue(type instanceof UnitType);
  }

  enum UnitType implements UnitTypeI {
    LENGTH,
    WEIGHT;
  }

  interface UnitTypeI {
  }

  /**
   * if you do not explicitly provide a factory method name via @MethodSource,
   * JUnit Jupiter will search for a factory method that has the same name
   * as the current @ParameterizedTest method by convention.
   * if the factory method located in other class,
   * it can be referenced by providing its fully qualified method name.
   */
  @ParameterizedTest
  @MethodSource("stringProvider")
  void testWithExplicitLocalMethodSource(String argument) {
    Assertions.assertNotNull(argument);
  }

  static Stream<String> stringProvider() {
    return Stream.of("apple", "banana");
  }

  /**
   * multiple parameters sample.
   */
  @ParameterizedTest
  @MethodSource("stringIntAndListProvider")
  void testWithMultiArgMethodSource(String str, Integer num, List<String> list) {
    Assertions.assertEquals(5, str.length());
    Assertions.assertTrue(num >= 1 && num <= 2);
    Assertions.assertEquals(2, list.size());
  }

  static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
        Arguments.arguments("lemon", 2, Arrays.asList("x", "y"))
    );
  }

  /**
   * description: implementations of the ParameterResolver can
   * provide parameter for factory methods.
   *
   * @author Justin_Zhang
   * @date 11/8/2022 1:37 PM
   */
  static class IntegerResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return parameterContext.getParameter().getType() == int.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
      return 2;
    }
  }

  @RegisterExtension
  static final IntegerResolver INTEGER_RESOLVER = new IntegerResolver();

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void test_with_factory_method_with_arguments(String argument) {
    Assertions.assertTrue(argument.startsWith("2"));
  }

  static Stream<Arguments> factoryMethodWithArguments(int quantity) {
    return Stream.of(
        Arguments.arguments(quantity + " apples"),
        Arguments.arguments(quantity + " lemons")
    );
  }

  /**
   * simple sample.
   */
  @ParameterizedTest
  @CsvSource({
      "apple,         1",
      "banana,        2",
      "'lemon, lime', 0xF1",
      "strawberry,    700_000"
  })
  void testWithCsvSource(String fruit, Integer rank) {
    Assertions.assertNotNull(fruit);
    Assertions.assertNotEquals(0, rank);
  }

  /**
   * Java SE 15 or higher, you can alternatively use the textBlock attribute of @CsvSource.
   */
  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvSource(useHeadersInDisplayName = true, textBlock = """
      FRUIT,         RANK
      apple,         1
      banana,        2
      'lemon, lime', 0xF1
      strawberry,    700_000
      """)
  void testWithCsvSourceByTextBlock(String fruit, Integer rank) {
    // ...
  }

  /**
   * using textBlock can contain comments with a # symbol.
   */
  @ParameterizedTest
  @CsvSource(delimiter = '|', quoteCharacter = '"', textBlock = """
      #-----------------------------
      #    FRUIT     |     RANK
      #-----------------------------
          # apple     |      1
      #-----------------------------
           banana    |      2
      #-----------------------------
        "lemon lime" |     0xF1
      #-----------------------------
         strawberry  |    700_000
      #-----------------------------
      """)
  void testWithCsvSourceByTextBlockWithComment(String fruit, Integer rank) {
    // ...
  }

  @ParameterizedTest
  @CsvFileSource(
      files = "src/test/java/com/justin/unittest/junit5/parameterizedtest/two-column.csv",
      numLinesToSkip = 1)
  void testWithCsvFileSourceFromFile(String country, Integer reference) {
    Assertions.assertNotNull(country);
    Assertions.assertNotEquals(0, reference);
  }

  @ParameterizedTest(name = "[{index}] {arguments}")
  @CsvFileSource(
      files = "src/test/java/com/justin/unittest/junit5/parameterizedtest/two-column.csv",
      useHeadersInDisplayName = true)
  void testWithCsvFileSourceAndHeaders(String country, Integer reference) {
    Assertions.assertNotNull(country);
    Assertions.assertNotEquals(0, reference);
  }

  /**
   * description: custom ArgumentsProvider
   * must be declared as either a top-level class or a static nested class.
   *
   * @author Justin_Zhang
   * @date 11/8/2022 2:10 PM
   */
  static class MyArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
      return Stream.of("apple", "banana").map(Arguments::of);
    }
  }

  @ParameterizedTest
  @ArgumentsSource(MyArgumentsProvider.class)
  void test_with_argument_source(String argument) {
    Assertions.assertNotNull(argument);
  }
}

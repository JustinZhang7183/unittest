package com.justin.unittest.junit5.parameterizedtest;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Description: the sample of custom display name test.
 * you can customize invocation display names via the name attribute
 * of the @ParameterizedTest annotation.
 *
 * @author Justin_Zhang
 * @date 11/9/2022 09:39
 */
public class CustomDisplayNameTest {
  /**
   * <p>a single quote (') needs to be represented as a doubled single quote ('')
   * in order to be displayed.</p>
   * placeholder:
   * <p>1.{displayName}: the display name of the method.</p>
   * <p>2.{index}: the current invocation index (1-based).</p>
   * <p>3.{arguments}: the complete,comma-separated arguments list.</p>
   * <p>4.{argumentWithNames}: the complete,comma-separated arguments list with parameter names.</p>
   * <p>5.{0},{1},...: an individual argument.</p>
   * <p>string representations are truncated if they exceed the configured maximum length.
   * configured in junit-platform.properties.</p>
   * <p>if you'd like to set a default name pattern for all parameterized tests
   * in your project, you can declare it in the junit-platform.properties</p>
   *
   */
  @DisplayName("Display name of container")
  @ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
  @CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
  void testWithCustomDisplayNames(String fruit, int rank) {
  }

  @DisplayName("A parameterized test with named arguments")
  @ParameterizedTest(name = "{index}: {0}")
  @MethodSource("namedArguments")
  void testWithNamedArguments(File file) {
  }

  /**
   * When using @MethodSource or @ArgumentsSource,
   * you can provide custom names for arguments using the Named API.
   */
  static Stream<Arguments> namedArguments() {
    return Stream.of(
        Arguments.arguments(Named.named("An important file", new File("path1"))),
        Arguments.arguments(Named.named("Another file", new File("path2")))
    );
  }
}

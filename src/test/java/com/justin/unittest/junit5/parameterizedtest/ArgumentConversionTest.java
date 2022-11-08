package com.justin.unittest.junit5.parameterizedtest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Description: sample of argument conversion test.
 * 1.widening conversion: for example,@ValueSource(ints = {1,2,3})
 * it can be declared to accept not only int but also long,float,double.
 * 2.implicit conversion: String are implicitly converted to some types
 * like byte,char,short,int,long and their boxed counterparts etc.
 * 3.explicit conversion: specify an ArgumentConverter to use for a certain
 * parameter using the @ConvertWith.
 *
 * @author Justin_Zhang
 * @date 11/8/2022 14:14
 */
public class ArgumentConversionTest {
  /**
   * JUnit Jupiter provides a fallback mechanism for automatic conversion
   * from a String to a given target type if target type declares exactly
   * one suitable factory method or a factory constructor as defined below.
   */
  @ParameterizedTest
  @ValueSource(strings = "42 Cats")
  void testWithImplicitFallbackArgumentConversion(Book book) {
    Assertions.assertEquals("42 Cats", book.getTitle());
  }

  static class Book {
    private final String title;

    private Book(String title) {
      this.title = title;
    }

    public static Book fromTitle(String title) {
      return new Book(title);
    }

    public String getTitle() {
      return this.title;
    }
  }

  /**
   * using @ConvertWith within a custom ArgumentConverter.
   */
  @ParameterizedTest
  @EnumSource(ChronoUnit.class)
  void testWithExplicitArgumentConversion(
      @ConvertWith(ToStringArgumentConverter.class) String argument) {
    Assertions.assertNotNull(ChronoUnit.valueOf(argument));
  }

  static class ToStringArgumentConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
      Assertions.assertEquals(String.class, targetType, "Can only convert to String");
      if (source instanceof Enum<?>) {
        return ((Enum<?>) source).name();
      }
      return String.valueOf(source);
    }
  }

  /**
   * description: if the convertor is only meant to convert one type to another
   * you can extend TypedArgumentConverter to avoid boilerplate type checks.
   *
   * @author Justin_Zhang
   * @date 11/8/2022 3:02 PM
   */
  static class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {
    protected ToLengthArgumentConverter() {
      super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) {
      return (source != null ? source.length() : 0);
    }
  }

  /**
   * junit-jupiter-params provides a single explicit argument converter.
   */
  @ParameterizedTest
  @ValueSource(strings = { "01.01.2017", "31.12.2017" })
  void testWithExplicitJavaTimeConverter(
      @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
    Assertions.assertEquals(2017, argument.getYear());
  }
}

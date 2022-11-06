package com.justin.unittest.junit5.basic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Description: meta annotation and composed annotation unit test.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 14:49
 */
public class MetaAndComposedAnnotationTest {
  /**
   * description: composed annotation for test and tag.
   *
   * @author Justin_Zhang
   * @date 11/6/2022 2:53 PM
   */
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @Tag("fast")
  @Test
  public @interface FastTest {
  }

  @FastTest
  public void if_composed_annotation_worked() {
    Assertions.assertEquals(1, 1, "1 always equal 1");
  }
}

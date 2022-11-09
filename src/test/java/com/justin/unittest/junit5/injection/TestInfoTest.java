package com.justin.unittest.junit5.injection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * description: the sample of using testInfo which is injected in method or constructor.
 * <p>
 * 1.TestInfoParameterResolver
 * 2.RepetitionInfoParameterResolver
 * 3.TestReportParameterResolver
 * beside these three resolver, others must be explicitly enabled
 * by registering appropriate extensions via @ExtendWith.
 * </p>
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:35 PM
 */
@DisplayName("TestInfo Demo")
public class TestInfoTest {
  TestInfoTest(TestInfo testInfo) {
    assertEquals("TestInfo Demo", testInfo.getDisplayName());
  }

  @BeforeEach
  void init(TestInfo testInfo) {
    String displayName = testInfo.getDisplayName();
    assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"));
  }

  @Test
  @DisplayName("TEST 1")
  @Tag("my-tag")
  void test1(TestInfo testInfo) {
    assertEquals("TEST 1", testInfo.getDisplayName());
    assertTrue(testInfo.getTags().contains("my-tag"));
  }

  @Test
  @Disabled("just for demonstrate, "
      + "disabled to change configuration for display name without influence")
  void test2() {
  }
}

package com.justin.unittest.junit5.injection;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

/**
 * description: the sample of using testReporter which is injected in method or constructor.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:40 PM
 */
public class TestReporterTest {
  @Test
  void reportSingleValue(TestReporter testReporter) {
    testReporter.publishEntry("a status message");
  }

  @Test
  void reportKeyValuePair(TestReporter testReporter) {
    testReporter.publishEntry("a key", "a value");
  }

  @Test
  void reportMultipleKeyValuePairs(TestReporter testReporter) {
    Map<String, String> values = new HashMap<>();
    values.put("user name", "dk38");
    values.put("award year", "1974");

    testReporter.publishEntry(values);
  }
}

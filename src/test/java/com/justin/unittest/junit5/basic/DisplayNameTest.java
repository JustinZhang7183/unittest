package com.justin.unittest.junit5.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * Description: <p>display name unit test</p>
 * <p>1.@DisplayName</p>
 * <p>2.Display name Generator</p>
 * <p>3.config default display name generator in junit-platform.properties</p>
 * the priority order is the same as show order above.
 * <p>nb: @IndicativeSentencesGeneration can set up alternative character as separator</p>
 *
 * @author Justin_Zhang
 * @date 11/6/2022 15:09
 */
@DisplayName("A special test case")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@IndicativeSentencesGeneration(separator = ":",
//    generator = DisplayNameGenerator.ReplaceUnderscores.class)
public class DisplayNameTest {
  @Test
  @DisplayName("display name is same as annotation")
  void if_display_name_same_as_annotation(TestInfo testInfo) {
    Assertions.assertEquals("display name is same as annotation", testInfo.getDisplayName());
  }

  @Test
  void if_display_name_replace_under_scores_with_blank(TestInfo testInfo) {
    Assertions.assertEquals("if display name replace under scores with blank (TestInfo)",
        testInfo.getDisplayName());
  }
}

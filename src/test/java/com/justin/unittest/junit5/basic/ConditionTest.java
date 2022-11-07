package com.justin.unittest.junit5.basic;


import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledInNativeImage;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/**
 * description: sample of using condition annotation within unit test.
 * When @EnabledIf or @DisabledIf is used at class level,
 * the condition method must always be static.
 * Condition methods located in external classes must also be static.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 2:14 PM
 */
@Slf4j
public class ConditionTest {
  @Test
  @EnabledOnOs(OS.MAC)
  void only_in_mac() {
    log.info("mac");
  }

  @Test
  @EnabledOnOs(OS.WINDOWS)
  void only_in_windows() {
    log.info("windows");
  }

  @Test
  @EnabledOnOs(architectures = "x86_64")
  void only_in_x86_64() {
    log.info("x86_64");
  }

  @Test
  @EnabledOnJre(JRE.JAVA_8)
  void only_in_java8() {
    log.info("java8");
  }

  @Test
  @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
  void fromJava9to11() {
    log.info("9-11");
  }

  @Test
  @EnabledInNativeImage
  void onlyWithinNativeImage() {
    // ...
  }

  @Test
  @DisabledInNativeImage
  void neverWithinNativeImage() {
    // ...
  }

  @Test
  @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
  void onlyOn64BitArchitectures() {
    // ...
  }

  @Test
  @DisabledIfSystemProperty(named = "ci-server", matches = "true")
  void notOnCiServer() {
    // ...
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
  void onlyOnStagingServer() {
    // ...
  }

  @Test
  @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
  void notOnDeveloperWorkstation() {
    // ...
  }

  @Test
  @EnabledIf("customCondition")
  void enabled() {
    // ...
  }

  @Test
  @DisabledIf("customCondition")
  void disabled() {
    // ...
  }

  boolean customCondition() {
    return true;
  }
}

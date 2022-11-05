package com.justin.unittest.junit5.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.JRE.JAVA_9;

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

package com.justin.unittest.junit5.parallelexecution;

import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.Isolated;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

/**
 * Description: the sample of parallel test.
 * <p>
 * step:
 * 1.set junit.jupiter.execution.parallel.enabled configuration parameter to true.
 * 2.set mode to CONCURRENT through configuration or @Execution parameter.
 * </p>
 * <p>
 * notable things:
 * 1.you must ensure the test class is thread-safe if it use the Lifecycle.PER_CLASS mode
 * 2.concurrent execution might conflict with the configured execution order.
 * </p>
 * <p>
 * maximum thread pool size configuration:
 * 1. junit.jupiter.execution.parallel.config.strategy : dynamic or fixed
 * 2. junit.jupiter.execution.parallel.config.dynamic.factor : default to 1,cores multiply factor
 * 3. junit.jupiter.execution.parallel.config.fixed.parallelism : fixed thread number
 * 4. junit.jupiter.execution.parallel.config.custom.class : need to specify a custom
 * ParallelExecutionConfigurationStrategy implementation
 * </p>
 * <p>
 * if you have some test classes that need to run in isolation, you can use @Isolated
 * tests in such classes are executed sequentially without any other tests running at the same time.
 * </p>
 *
 * @author Justin_Zhang
 * @date 11/9/2022 14:04
 */
@Execution(ExecutionMode.CONCURRENT)
public class ParallelExecutionTest {
  private Properties backup;

  @BeforeEach
  void backup() {
    backup = new Properties();
    backup.putAll(System.getProperties());
  }

  @AfterEach
  void restore() {
    System.setProperties(backup);
  }

  /**
   * <p>
   * ResourceLock annotation allows you to declare that a test class or method uses a specific
   * shared resource that requires synchronized access to ensure reliable test execution.
   * </p>
   * <p>
   * The shared resource is identified by a unique name which is a String.
   * It can be user-defined or one of the predefined constants in Resources:
   * SYSTEM_PROPERTIES, SYSTEM_OUT, SYSTEM_ERR, LOCALE, or TIME_ZONE
   * </p>
   * <p>
   * two tests that require READ access to a shared resource may run in parallel
   * but not while any other test that requires READ_WRITE access to the same
   * shared resource is running.
   * </p>
   */
  @Test
  @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ)
  void customPropertyIsNotSetByDefault() {
    Assertions.assertNull(System.getProperty("my.prop"));
  }

  @Test
  @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
  void canSetCustomPropertyToApple() {
    System.setProperty("my.prop", "apple");
    Assertions.assertEquals("apple", System.getProperty("my.prop"));
  }

  @Test
  @ResourceLock(value = Resources.SYSTEM_PROPERTIES, mode = ResourceAccessMode.READ_WRITE)
  void canSetCustomPropertyToBanana() {
    System.setProperty("my.prop", "banana");
    Assertions.assertEquals("banana", System.getProperty("my.prop"));
  }
}

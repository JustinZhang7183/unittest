package com.justin.unittest.junit5.builtinextension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

/**
 * Description: temp dir test case.
 * <p>
 * TempDirectory extension is registered by default.
 * to create and clean up a temporary directory for an individual test or
 * all tests in a test class.
 * </p>
 * <p>
 * How to use:
 * 1.annotate a non-final, unassigned field of type java.nio.file.Path or java.io.File
 * 2.add a parameter annotated with @TempDir to a lifecycle or test method
 * </p>
 *
 * @author Justin_Zhang
 * @date 11/9/2022 14:52
 */
public class TempDirectoryExtensionTest {
  @Test
  void writeItemsToFile(@TempDir Path tempDir) throws IOException {
    Path path = tempDir.resolve("test.txt");
    Files.write(path, new byte[]{'a', 'b'});
    Assertions.assertEquals("ab", Files.readString(path));
  }

  /**
   * you can use two @TempDir parameters.
   */
  @Test
  void copyFileFromSourceToTarget(@TempDir Path source, @TempDir Path target) throws IOException {
    Path sourceFile = source.resolve("test.txt");
    Files.write(sourceFile, new byte[]{'a', 'b'});
    Path targetFile = Files.copy(sourceFile, target.resolve("text.txt"));
    Assertions.assertNotEquals(sourceFile, targetFile);
    Assertions.assertEquals("ab", Files.readString(targetFile));
  }

  /**
   * <p>
   * if you wish to retain a single reference to a temp directory across lifecycle methods
   * and current test method, use field injection.
   * you can use instance field so that each test method uses a separate directory.
   * </p>
   * <p>
   * there is an optional cleanup attribute: NEVER, ON_SUCCESS, ALWAYS
   * default is ALWAYS.
   * you also can config it in junit-platform.properties.
   * </p>
   */
  @TempDir(cleanup = CleanupMode.ALWAYS)
  static Path sharedTempDir;
}

package com.justin.unittest.junit5.testinterface;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * description: interface annotated by @ExtendWith to extend TimingExtension.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 5:44 PM
 */
@Tag("timed")
@ExtendWith(TimingExtension.class)
public interface TimeExecutionLogger {
}

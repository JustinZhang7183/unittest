package com.justin.unittest.junit5.parameterizedtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class ParameterizedTestDemo {
    @ParameterizedTest(autoCloseArguments = false)
    @ValueSource(strings = {"a","b"})
    void value_sourse_test(String str) {
        Assertions.assertTrue(str.isEmpty() || str.length() > 0);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @NullAndEmptySource
    void null_and_empty_test(String str) {
        Assertions.assertTrue(str == null || str.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(UnitType.class)
    void enum_test(UnitTypeI type) {
        Assertions.assertTrue(type instanceof UnitType);
    }

    enum UnitType implements UnitTypeI{
        LENGTH,
        WEIGHT;
    }

    interface UnitTypeI {

    }
}

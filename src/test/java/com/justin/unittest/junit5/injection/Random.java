package com.justin.unittest.junit5.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: custom annotation for Random.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:02 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Random {
}

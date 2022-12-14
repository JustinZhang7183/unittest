package com.justin.unittest.junit5.injection;

import java.lang.reflect.Parameter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * description: a custom ParameterResolver.
 *
 * @author Justin_Zhang
 * @date 11/7/2022 3:45 PM
 */
public class RandomParametersExtension implements ParameterResolver {
  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.isAnnotated(Random.class);
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) throws ParameterResolutionException {
    return getRandomValue(parameterContext.getParameter(), extensionContext);
  }

  private Object getRandomValue(Parameter parameter, ExtensionContext extensionContext) {
    Class<?> type = parameter.getType();
    java.util.Random random = extensionContext.getRoot().getStore(ExtensionContext.Namespace.GLOBAL)
        .getOrComputeIfAbsent(java.util.Random.class);
    if (int.class.equals(type)) {
      return random.nextInt();
    }
    if (double.class.equals(type)) {
      return random.nextDouble();
    }
    throw new ParameterResolutionException("No random generator implemented for " + type);
  }
}

package com.justin.unittest.junit5.parameterizedtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Description: sample of argument aggregation test.
 * an instance of ArgumentAccessor is automatically injected
 * into any parameter of type ArgumentAccessor.
 *
 * @author Justin_Zhang
 * @date 11/8/2022 15:06
 */
public class ArgumentAggregationTest {
  @ParameterizedTest
  @CsvSource({
      "Jane, Doe, F, 1990-05-20",
      "John, Doe, M, 1990-10-22"
  })
  void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
    Person person = new Person(arguments.getString(0),
        arguments.getString(1),
        arguments.get(2, Gender.class),
        arguments.get(3, LocalDate.class));
    if (person.getFirstName().equals("Jane")) {
      Assertions.assertEquals(Gender.F, person.getGender());
    } else {
      Assertions.assertEquals(Gender.M, person.getGender());
    }
    Assertions.assertEquals("Doe", person.getLastName());
    Assertions.assertEquals(1990, person.getDateOfBirth().getYear());
  }

  /**
   * description: custom aggregator for person.
   *
   * @author Justin_Zhang
   * @date 11/9/2022 9:35 AM
   */
  static class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor argumentsAccessor,
        ParameterContext parameterContext) throws ArgumentsAggregationException {
      return new Person(argumentsAccessor.getString(0),
          argumentsAccessor.getString(1),
          argumentsAccessor.get(2, Gender.class),
          argumentsAccessor.get(3, LocalDate.class));
    }
  }

  @ParameterizedTest
  @CsvSource({
      "Jane, Doe, F, 1990-05-20",
      "John, Doe, M, 1990-10-22"
  })
  void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
    if (person.getFirstName().equals("Jane")) {
      Assertions.assertEquals(Gender.F, person.getGender());
    } else {
      Assertions.assertEquals(Gender.M, person.getGender());
    }
    Assertions.assertEquals("Doe", person.getLastName());
    Assertions.assertEquals(1990, person.getDateOfBirth().getYear());
  }

  /**
   * description: composed annotation for @AggregateWith(PersonAggregator.class).
   *
   * @author Justin_Zhang
   * @date 11/9/2022 9:37 AM
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.PARAMETER)
  @AggregateWith(PersonAggregator.class)
  public @interface CsvToPerson {
  }

  void test_argument_aggregator(@CsvToPerson Person person) {
  }

  static class Person {
    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

    public Person() {
    }

    public Person(String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.gender = gender;
      this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public Gender getGender() {
      return gender;
    }

    public void setGender(Gender gender) {
      this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
      return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
    }
  }

  static enum Gender {
    F,
    M;
  }
}

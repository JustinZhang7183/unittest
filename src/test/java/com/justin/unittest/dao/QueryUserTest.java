package com.justin.unittest.dao;

import com.justin.unittest.UnittestApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description: query unit test of persistence layer of user.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:22 PM
 */
@SpringBootTest(classes = UnittestApplication.class)
public class QueryUserTest {

  @Autowired
  private UserDao userDao;

  @Test
  void if_stan_is_20_years_old() {
    Integer age = userDao.queryByName("stan").getAge();
    Assertions.assertEquals(20, age, "stan is 20 years old");
    Assertions.assertEquals(20, (int) age, () -> "stan is 20 years old");
  }

  @Test
  void if_stan_phone_number_is_not_13900000() {
    Assertions.assertNotEquals("13900000", userDao.queryByName("stan").getPhoneNumber(),
        "stan's phone number is not 13900000");
  }
}

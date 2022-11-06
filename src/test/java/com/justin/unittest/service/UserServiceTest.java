package com.justin.unittest.service;

import com.justin.unittest.UnittestApplication;
import com.justin.unittest.exception.DuplicateUserException;
import com.justin.unittest.po.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description: unit test of service of user.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:37 PM
 */
@SpringBootTest(classes = UnittestApplication.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  void if_insert_same_user_throw_same_user_exception() {
    Assertions.assertThrows(DuplicateUserException.class, () -> userService.insert(new User(1L,
        "Tom", 15, "150000")));
  }
}

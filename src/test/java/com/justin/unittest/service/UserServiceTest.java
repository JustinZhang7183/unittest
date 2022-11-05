package com.justin.unittest.service;

import com.justin.unittest.UnittestApplication;
import com.justin.unittest.exception.DuplicateUserException;
import com.justin.unittest.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UnittestApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void if_insert_same_user_throw_same_user_exception() {
        Assertions.assertThrows(DuplicateUserException.class, () -> userService.insert());
    }
}

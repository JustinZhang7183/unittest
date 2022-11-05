package com.justin.unittest.junit5.basic;

import com.justin.unittest.UnittestApplication;
import com.justin.unittest.dao.UserDao;
import com.justin.unittest.po.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UnittestApplication.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = ":", generator = DisplayNameGenerator.IndicativeSentences.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InitializeTableTest {
    @Autowired
    private UserDao userDao;

    @Test
    @Order(1)
    void if_truncate_user_table_success() {
        userDao.truncateUserTable();
    }

    @Test
    @DisplayName("initialize user table test case")
    @Order(2)
    void if_initialize_user_table_success() {
        userDao.initializeUserTable();
    }

    @Test
    @Order(3)
    void if_insert_stan_item_success() {
        userDao.insert(new User(1L,"stan",20,"13800000000"));
    }

}

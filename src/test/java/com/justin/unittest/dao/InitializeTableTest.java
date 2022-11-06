package com.justin.unittest.dao;

import com.justin.unittest.UnittestApplication;
import com.justin.unittest.po.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description: initialization table unit test of persistent layer.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:29 PM
 */
@SpringBootTest(classes = UnittestApplication.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = ":",
    generator = DisplayNameGenerator.IndicativeSentences.class)
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
    userDao.insert(new User(1L, "stan", 20, "13800000000"));
  }

}

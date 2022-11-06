package com.justin.unittest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: unit test runner.
 *
 * @author Justin_Zhang
 * @date 11/6/2022 2:18 PM
 */
@SpringBootApplication
@MapperScan("com.justin.unittest.dao")
public class UnittestApplication {
  /**
   * description: main function of runner.
   *
   * @param args parameter of runner
   */
  public static void main(String[] args) {
    SpringApplication.run(UnittestApplication.class, args);
  }

}

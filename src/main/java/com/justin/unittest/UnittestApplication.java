package com.justin.unittest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.justin.unittest.dao")
public class UnittestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnittestApplication.class, args);
    }

}

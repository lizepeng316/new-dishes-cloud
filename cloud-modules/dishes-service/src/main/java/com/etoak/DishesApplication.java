package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.etoak.**.mapper")
@EnableTransactionManagement
public class DishesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DishesApplication.class, args);
    }
}

package com.duotest;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class
})
public class TestSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringApplication.class, args);
    }

}

package com.springboot.apirestmysqldocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages = "com.springboot.apirestmysqldocker")
@EntityScan(basePackages = "com.springboot.apirestmysqldocker.model")
public class ApiRestMysqlDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestMysqlDockerApplication.class, args);
    }

}

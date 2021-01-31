package com.rocky.boot.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rocky.boot.api.mapper")
@SpringBootApplication
public class SpringbootApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApiServiceApplication.class, args);
    }

}

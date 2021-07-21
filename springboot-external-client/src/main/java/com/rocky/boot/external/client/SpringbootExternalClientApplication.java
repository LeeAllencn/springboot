package com.rocky.boot.external.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rocky
 */
@SpringBootApplication
@ComponentScan(value = "com.rocky.boot")
public class SpringbootExternalClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExternalClientApplication.class, args);
    }

}

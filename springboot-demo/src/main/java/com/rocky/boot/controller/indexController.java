package com.rocky.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 2017/8/6.
 */
@RestController
public class indexController {

    @RequestMapping("/")
    public String index(){
        return "Hello Spring Boot!";
    }
}

package com.rocky.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 2017/8/6.
 *
 * 页面访问控制
 */
@RestController //规定controller里面的方法都以json格式输出
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "Hello Spring Boot!";
    }
}

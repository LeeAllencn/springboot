package com.rocky.boot.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by Rocky on 2017-12-12.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "hello " + name;
    }
}

package com.rocky.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 2017-11-23.
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/cors", method = RequestMethod.GET)
    public String index() {
        return "this is cors info";
    }
}

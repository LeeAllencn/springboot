package com.rocky.boot.controller;

import com.rocky.boot.model.User;
import com.rocky.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Rocky on 2017-12-12.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Object save(HttpServletRequest servletRequest) {
        User user =new User();
        user.setUsername("abel");
        userService.add(user);
        return "ok";
    }
}

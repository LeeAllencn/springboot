package com.rocky.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rocky on 2017/8/6.
 *
 * 页面访问控制
 */
@Api(value = "IndexController", description = "index interface")
@RestController //规定controller里面的方法都以json格式输出
public class IndexController {

    @ApiOperation(value = "初始页面", notes = "初始页面接口")
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String index(){
        return "Hello Spring Boot!";
    }
}

package com.rocky.boot.springbootdubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.rocky.boot.interfaces.HelloService;

/**
 * @author lixin
 * @Description:
 * @Date: Created in 2018/9/5
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello , " + name;
    }
}

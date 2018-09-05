package com.rocky.boot.springbootdubbocustomer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocky.boot.interfaces.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author lixin
 * @Description:
 * @Date: Created in 2018/9/5
 */
@Component
public class ServiceProxy {
    @Reference(version = "1.0.0")
    public HelloService helloService;
}

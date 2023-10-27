package com.rocky.boot.springbootdubbocustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author rocky
 */
@SpringBootApplication
public class SpringbootDubboCustomerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootDubboCustomerApplication.class, args);
        ServiceProxy proxy = ctx.getBean(ServiceProxy.class);
        //调用服务
        System.out.println(proxy.helloService.sayHello("rocky"));
    }
}

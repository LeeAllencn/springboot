package com.rocky.boot.grpc.client;

import com.rocky.boot.common.utils.GrpcBeanUtils;
import com.rocky.boot.common.utils.JsonUtil;
import com.rocky.boot.grpc.interfaces.HelloRequest;
import com.rocky.boot.grpc.interfaces.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringbootGrpcClientApplicationTests {

    @GrpcClient("springboot-grpc-server")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    @Test
    void contextLoads() {
    }

    @Test
    void receiveGreeting() {
        String name = "rocky";
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        String message = myServiceStub.sayHello(request).getMessage();
        System.out.println(message);
    }

    // proto3 字段默认值转换为pojo对象被忽略问题修复测试
    @Test
    void toPojoTest() throws IOException {
        String name = "";
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        Hello hello = GrpcBeanUtils.toPojoBean(request, Hello.class);
        System.out.println(JsonUtil.toJson(hello));
    }

}

package com.rocky.boot.grpc.client;

import com.rocky.boot.grpc.interfaces.HelloRequest;
import com.rocky.boot.grpc.interfaces.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}

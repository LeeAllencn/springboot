package com.rocky.boot.grpc.client;

import com.rocky.boot.grpc.interfaces.HelloRequest;
import com.rocky.boot.grpc.interfaces.MyServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/1
 */
@Service
public class FoobarService {

    @GrpcClient("springboot-grpc-server")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    public String receiveGreeting(String name) {
        HelloRequest request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        return myServiceStub.sayHello(request).getMessage();
    }
}

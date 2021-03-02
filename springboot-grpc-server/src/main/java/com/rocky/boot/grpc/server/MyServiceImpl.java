package com.rocky.boot.grpc.server;

import com.rocky.boot.grpc.interfaces.HelloReply;
import com.rocky.boot.grpc.interfaces.HelloRequest;
import com.rocky.boot.grpc.interfaces.MyServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/1
 */
@GrpcService
public class MyServiceImpl extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello ==> " + request.getName())
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

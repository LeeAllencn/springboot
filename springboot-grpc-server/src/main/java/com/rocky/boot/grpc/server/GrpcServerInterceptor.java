package com.rocky.boot.grpc.server;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/2
 */
@Slf4j
@GrpcGlobalServerInterceptor
public class GrpcServerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        // TODO 日志记录
        // TODO grpc请求处理
        Metadata.Key<String> key1 = Metadata.Key.of("key1", Metadata.ASCII_STRING_MARSHALLER);
        String value1 = metadata.get(key1);

        Metadata.Key<String> key2 = Metadata.Key.of("key2", Metadata.ASCII_STRING_MARSHALLER);
        String value2 = metadata.get(key2);
        log.info(value1);
        log.info(value2);
        return serverCallHandler.startCall(serverCall, metadata);
    }
}

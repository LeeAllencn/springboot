package com.rocky.boot.common.exception;

/**
 * @author rocky
 * Description: 自身服务运行异常
 * Created in 2021/2/9
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

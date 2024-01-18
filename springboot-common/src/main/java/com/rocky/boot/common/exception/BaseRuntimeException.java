package com.rocky.boot.common.exception;

/**
 * 自定义基础运行时异常
 * @author : rocky
 * @date : created in 2024/1/17
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String message) {
        super(message);
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

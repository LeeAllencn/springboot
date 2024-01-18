package com.rocky.boot.common.exception;

/**
 * 格式化异常
 * @author : rocky
 * @date : created in 2024/1/17
 */
public class FormatException extends BaseRuntimeException {

    public FormatException() {
    }

    public FormatException(String message) {
        super(message);
    }

    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatException(Throwable cause) {
        super(cause);
    }

    public FormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

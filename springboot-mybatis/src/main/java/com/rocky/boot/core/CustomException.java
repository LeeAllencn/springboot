package com.rocky.boot.core;

/**
 * @author rocky
 * @Description: 自定义异常
 * @Date: Created in 2019/3/14
 */
public class CustomException extends RuntimeException {
    
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

}

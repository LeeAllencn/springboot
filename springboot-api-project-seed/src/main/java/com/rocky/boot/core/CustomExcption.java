package com.rocky.boot.core;

/**
 * @author rocky
 * @Description: 自定义异常
 * @Date: Created in 2019/3/14
 */
public class CustomExcption extends RuntimeException {
    public CustomExcption() {
        super();
    }

    public CustomExcption(String message) {
        super(message);
    }

    public CustomExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomExcption(Throwable cause) {
        super(cause);
    }

}

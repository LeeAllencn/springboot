package com.rocky.boot.common.exception;

/**
 * @author : rocky
 * @date : created in 2024/1/12
 */
public class ParameterVerificationException extends RuntimeException {

    public ParameterVerificationException(String message) {
        super(message);
    }

    public ParameterVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}

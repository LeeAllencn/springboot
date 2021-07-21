package com.rocky.boot.external.client.exceptions;

/**
 * @author : rocky
 * @date : created in 2021/7/1
 */
public class ExternalServiceException extends RuntimeException{

    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

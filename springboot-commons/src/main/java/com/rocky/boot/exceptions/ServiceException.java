package com.rocky.boot.exceptions;

import com.rocky.boot.exceptions.BaseRuntimeException;

/**
 * Created by Rocky on 2017-10-10.
 */
public class ServiceException extends BaseRuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2306646549503586920L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}

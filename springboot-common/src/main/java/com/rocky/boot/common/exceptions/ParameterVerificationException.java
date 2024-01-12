package com.rocky.boot.common.exceptions;

/**
 * @author : rocky
 * @date : created in 2024/1/12
 */
public class ParameterVerificationException extends RuntimeException {

    /**
     * 业务编码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    public ParameterVerificationException(String message) {
        super(message);
    }

    public ParameterVerificationException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

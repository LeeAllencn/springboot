package com.rocky.boot.common.exception;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

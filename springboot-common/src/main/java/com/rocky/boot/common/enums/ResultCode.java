package com.rocky.boot.common.enums;

/**
 * @author rocky
 * Description: 自定义结果返回码
 * Created in 2021/1/29
 */
public enum ResultCode {

    SUCCESS("A00000", "SUCCESS", 200),
    NOT_FOUND("A00404", "Not Found", 404),
    INTERNAL_SERVER_ERROR("A00500", "Internal Server Error", 500);

    private String code;
    private String message;
    private Integer statusCode;

    ResultCode(String code, String message, Integer statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}

package com.rocky.boot.results;

/**
 * @author rocky
 * @Description: 响应状态封装
 * @Date: Created in 2019/3/14
 */
public class ResponseStatus {

    /**
     * 操作是否成功
     */
    private Boolean isSuccess;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    public ResponseStatus(ResponseCodeEnum codeEnum) {
        this.isSuccess = codeEnum.getSuccess();
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.rocky.boot.common.model;

import lombok.Data;

/**
 * @author rocky
 * Description: 接口通用返回模型
 * Created in 2021/1/29
 */
@Data
public class BaseResult<T> {

    private String code;

    private String message;

    private T data;

    private String requestId;

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

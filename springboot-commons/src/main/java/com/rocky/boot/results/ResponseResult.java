package com.rocky.boot.results;

/**
 * @author rocky
 * @Description: 统一API响应结果封装
 * @Date: Created in 2019/3/14
 */
public class ResponseResult {

    private ResponseStatus status;

    private Object data;

    public ResponseStatus getStatus() {
        return status;
    }

    public ResponseResult setStatus(ResponseStatus status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseResult setData(Object data) {
        this.data = data;
        return this;
    }
}

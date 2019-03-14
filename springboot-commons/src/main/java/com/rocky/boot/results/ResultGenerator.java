package com.rocky.boot.results;

/**
 * @author rocky
 * @Description: 响应结果生成工具
 * @Date: Created in 2019/3/14
 */
public class ResultGenerator {

    public static ResponseResult genSuccessResult() {
        return new ResponseResult()
                .setStatus(new ResponseStatus(ResponseCodeEnum.SUCCESS));
    }

    public static ResponseResult genSuccessResult(Object data) {
        return new ResponseResult()
                .setStatus(new ResponseStatus(ResponseCodeEnum.SUCCESS))
                .setData(data);
    }

    public static ResponseResult genFailResult(ResponseCodeEnum codeEnum) {
        return new ResponseResult()
                .setStatus(new ResponseStatus(codeEnum));
    }
}

package com.rocky.boot.common.model;

import com.rocky.boot.common.enums.ResultCode;

/**
 * @author rocky
 * Description: 响应结果生成工具
 * Created in 2021/1/29
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static <E> BaseResult<E> genSuccessResult() {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), DEFAULT_SUCCESS_MESSAGE);
    }

    public static <E> BaseResult<E> genSuccessResult(E data) {
        return new BaseResult<>(ResultCode.SUCCESS.getCode(), DEFAULT_SUCCESS_MESSAGE, data);
    }
}

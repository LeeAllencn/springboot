package com.rocky.boot.common.model;

import com.rocky.boot.common.enums.ResultCodeEnum;

/**
 * @author rocky
 * Description: 响应结果生成工具
 * Created in 2021/1/29
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static <E> BaseResult<E> genSuccessResult() {
        return new BaseResult<>(ResultCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS_MESSAGE, null);
    }

    public static <E> BaseResult<E> genSuccessResult(E data) {
        return new BaseResult<>(ResultCodeEnum.SUCCESS.getCode(), DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static <E> BaseResult<E> getFailResult(ResultCodeEnum resultCodeEnum, Object... args) {
        return new BaseResult<>(resultCodeEnum.getCode(), resultCodeEnum.getWebMessage(args), null);
    }
}

package com.rocky.boot.results;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lixin
 * @Description:
 * @Date: Created in 2019/3/14
 */
public class Test {

    public static void main(String[] args) {
        ResponseResult responseResult = new ResponseResult();
        ResponseStatus responseStatus = new ResponseStatus(ResponseCodeEnum.SUCCESS);
        responseResult.setStatus(responseStatus);
        responseResult.setData("lixin");
        String json = JSONObject.toJSONString(responseResult);
        System.out.println(json);

        System.out.println(JSONObject.toJSONString(ResultGenerator.genSuccessResult()));

        System.out.println(JSONObject.toJSONString(ResultGenerator.genSuccessResult("rocky")));

        System.out.println(JSONObject.toJSONString(ResultGenerator.genFailResult(ResponseCodeEnum.FAIL)));
    }
}

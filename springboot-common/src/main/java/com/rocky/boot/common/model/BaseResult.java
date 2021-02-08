package com.rocky.boot.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rocky
 * Description: 接口通用返回模型
 * Created in 2021/1/29
 */
@Data
@ApiModel(value = "基本响应模型")
public class BaseResult<T> {

    @ApiModelProperty(value = "业务编码")
    private String code;

    @ApiModelProperty(value = "提示信息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "请求ID")
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

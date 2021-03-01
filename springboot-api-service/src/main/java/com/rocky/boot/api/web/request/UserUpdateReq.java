package com.rocky.boot.api.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author rocky
 * Description:
 * Created in 2021/2/26
 */
@Data
@ApiModel(value = "更新用户请求模型")
public class UserUpdateReq {

    @ApiModelProperty(value = "密码")
    @Size(min = 6, message = "密码长度不能低于6个字符")
    private String password;

    @ApiModelProperty(value = "电话号码")
    private String telephone;
}

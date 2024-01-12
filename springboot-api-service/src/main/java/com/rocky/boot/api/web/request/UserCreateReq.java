package com.rocky.boot.api.web.request;

import com.rocky.boot.common.validator.EnumParameterValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author rocky
 * Description: 创建用户请求模型
 * Created in 2021/1/30
 */
@Data
@ApiModel(value = "创建用户请求模型")
public class UserCreateReq {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 63, message = "用户名在3-63个字符长度之间")
    @Pattern(regexp = "^[a-z][a-z0-9\\-]{1,61}[a-z0-9]$", message = "输入3-63个字符，可以包含小写英文字母、数字和中划线（-），并以小写字母开头，小写字母或者数字结尾。")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能低于6个字符")
    private String password;

    @ApiModelProperty(value = "性别:0-女;1-男")
    @NotNull(message = "性别不能为空")
    @EnumParameterValidator(values = "0,1", message = "性别只能传0或1")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    private String telephone;
}

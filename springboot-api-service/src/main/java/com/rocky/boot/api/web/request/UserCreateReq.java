package com.rocky.boot.api.web.request;

import com.rocky.boot.common.validator.EnumParameterValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author rocky
 * Description: 创建用户请求模型
 * Created in 2021/1/30
 */
@Data
public class UserCreateReq {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 10, message = "用户名在2-10个字符长度之间")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能低于6个字符")
    private String password;

    /**
     * 性别：0-女；1-男
     */
    @NotNull(message = "性别不能为空")
    @EnumParameterValidator(values = "0,1", message = "性别只能传0或1")
    private Boolean sex;

    /**
     * 电话号码
     */
    @NotNull(message = "电话不能为空")
    private String telephone;
}

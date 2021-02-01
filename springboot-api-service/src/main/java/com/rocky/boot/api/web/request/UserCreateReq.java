package com.rocky.boot.api.web.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author rocky
 * Description:
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
    private String password;

    /**
     * 性别：0-女；1-男
     */
    private Boolean sex;

    /**
     * 电话号码
     */
    private String telephone;
}

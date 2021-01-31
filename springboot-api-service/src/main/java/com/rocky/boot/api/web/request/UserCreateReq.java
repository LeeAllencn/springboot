package com.rocky.boot.api.web.request;

import lombok.Data;

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
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：0-女；1-男
     */
    private Byte sex;

    /**
     * 电话号码
     */
    private String telephone;
}

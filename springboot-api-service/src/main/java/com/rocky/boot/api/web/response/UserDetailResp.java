package com.rocky.boot.api.web.response;

import lombok.Data;

import java.util.Date;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/29
 */
@Data
public class UserDetailResp {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别：0-女；1-男
     */
    private Boolean sex;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}

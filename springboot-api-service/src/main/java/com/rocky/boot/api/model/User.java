package com.rocky.boot.api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rocky
 */
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别:0-女;1-男
     */
    private Integer sex;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 是否删除:1 表示删除;0 表示未删除
     */
    @TableField("is_deleted")
    private Boolean deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
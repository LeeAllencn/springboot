package com.rocky.boot.api.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author rocky
 * Description:
 * Created in 2021/1/29
 */
@Data
@ApiModel(value = "用户详情响应模型")
public class UserDetailResp {

    @ApiModelProperty(value = "用户ID")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "性别：0-女；1-男")
    private Integer sex;

    @ApiModelProperty(value = "电话号码")
    private String telephone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}

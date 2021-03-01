package com.rocky.boot.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author rocky
 * Description: 分页请求参数对象
 * Created in 2021/2/24
 */
@Data
@ApiModel(value = "分页请求参数对象")
public class PageParam {

    @ApiModelProperty(value = "页码")
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    @Min(1)
    @Max(500)
    private Integer pageSize;

    public PageParam() {
        this.pageNum = 1;
        this.pageSize = 10;
    }
}

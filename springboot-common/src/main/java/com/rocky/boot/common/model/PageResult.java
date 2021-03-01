package com.rocky.boot.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author rocky
 * Description: 返回分页模型
 * Created in 2021/1/29
 */
@Data
@ApiModel(value = "分页模型")
public class PageResult<T> {

    @ApiModelProperty(value = "返回数据")
    private List<T> data;

    @ApiModelProperty(value = "当前页码")
    private long currentPage;

    @ApiModelProperty(value = "当前页显示数量")
    private long pageSize;

    @ApiModelProperty(value = "数据总数量")
    private long total;
}
